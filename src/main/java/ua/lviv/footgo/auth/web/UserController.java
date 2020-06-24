package ua.lviv.footgo.auth.web;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.lviv.footgo.auth.model.User;
import ua.lviv.footgo.auth.repository.UserRepository;
import ua.lviv.footgo.auth.service.EmailService;
import ua.lviv.footgo.auth.service.SecurityService;
import ua.lviv.footgo.auth.service.UserService;
import ua.lviv.footgo.auth.validator.UserForgotPasswordValidator;
import ua.lviv.footgo.auth.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.nulabinc.zxcvbn.Strength;
import com.nulabinc.zxcvbn.Zxcvbn;
import ua.lviv.footgo.entity.Season;
import ua.lviv.footgo.entity.Tournament;
import ua.lviv.footgo.repository.TournamentRepository;

@Controller
public class UserController {
    //private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private UserForgotPasswordValidator userForgotPasswordValidator;

    @Autowired
    private EmailService emailService;

    @Autowired
    TournamentRepository tournamentRepository;


    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") @Valid User userForm, BindingResult bindingResult, Model model, HttpServletRequest request) {
        userValidator.validate(userForm, bindingResult);
        System.out.println("UserForm " + userForm.getEmail() + userForm.getFirstName() + userForm.getLastName() + userForm.getPassword() + userForm.getPasswordConfirm());

        User userExists = userService.findByEmail(userForm.getEmail());
        System.out.println(userExists);

        if (userExists != null) {
            model.addAttribute("alreadyRegisteredMessage", "Oops!  There is already a user registered with the email provided");
            bindingResult.reject("email");
            return "registration";
        }

        if (bindingResult.hasErrors()) {
            System.out.println("Errors " + bindingResult.hasErrors());
            return "registration";
        } else {
            userForm.setEnabled(false);
            userForm.setConfirmationToken(UUID.randomUUID().toString());

            userService.save(userForm);

            String appUrl = request.getScheme() + "://" + request.getServerName();

            SimpleMailMessage registrationEmail = new SimpleMailMessage();
            registrationEmail.setTo(userForm.getEmail());
            registrationEmail.setSubject("Registration Confirmation");
            registrationEmail.setText("To confirm your e-mail address, please click the link below:\n"
                    + appUrl + "/confirm?token=" + userForm.getConfirmationToken());
            registrationEmail.setFrom("noreply@domain.com");
            emailService.sendEmail(registrationEmail);
            model.addAttribute("confirmationMessage", "A confirmation e-mail has been sent to " + userForm.getEmail());
        }

/*        userService.save(userForm);*/

        //securityService.autoLogin(userForm.getEmail(), userForm.getPasswordConfirm());

        return "redirect:/login";
    }

    @GetMapping(value="/confirm")
    public ModelAndView showConfirmationPage(ModelAndView modelAndView, @RequestParam("token") String token) {

        User user = userService.findByConfirmationToken(token);

        if (user == null) { // No token found in DB
            modelAndView.addObject("invalidToken", "Oops!  This is an invalid confirmation link");
        } else { // Token found
            modelAndView.addObject("confirmationToken", user.getConfirmationToken());
        }

        modelAndView.setViewName("confirm");
        return modelAndView;
    }

    @PostMapping(value="/confirm")
    public ModelAndView processConfirmationForm(ModelAndView modelAndView, BindingResult bindingResult, @RequestParam Map requestParams, RedirectAttributes redirect) {

        modelAndView.setViewName("confirm");

        //Zxcvbn passwordCheck = new Zxcvbn();

        //Strength strength = passwordCheck.measure((CharSequence) requestParams.get("password"));

/*        if (strength.getScore() < 3) {
            bindingResult.reject("password");

            redirect.addFlashAttribute("errorMessage", "Your password is too weak.  Choose a stronger one.");

            modelAndView.setViewName("redirect:confirm?token=" + requestParams.get("token"));
            System.out.println(requestParams.get("token"));
            return modelAndView;
        }*/

        // Find the user associated with the reset token
        User user = userService.findByConfirmationToken((String) requestParams.get("token"));

        if (user.getEnabled()) {
            modelAndView.addObject("successMessage", "Already confirmed!");

            return modelAndView;
        }

        // Set new password
        //user.setPassword(bCryptPasswordEncoder.encode((CharSequence) requestParams.get("password")));
        //user.setPassword(user.getPassword());

        // Set user to enabled
        user.setEnabled(true);
        // Save user
        userRepository.save(user);

        //modelAndView.addObject("successMessage", "Successfully confirmed!");
        modelAndView.setViewName("redirect:login");
        return modelAndView;
    }

    @GetMapping("/login")
    public String userLogin(Model model, String error, String logout) {

        if (error != null)
            model.addAttribute("error", "Your email and password is invalid");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully");

        return "userLogin";
    }

    @GetMapping({"/profile"})
    public String profile(Model model) {
        List<Tournament> tournaments = (List<Tournament>) tournamentRepository.findAll();
        Tournament tournament = tournaments.get(0);
        model.addAttribute("tournament", tournament);
        Season season = tournament.getActiveSeason();
        model.addAttribute("season", season);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUser = authentication.getAuthorities().toString();
            model.addAttribute("currentUser", currentUser);
        }

        User user = userRepository.findByEmail(authentication.getName());
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        Date createdOn = user.getCreatedOn();

        model.addAttribute("firstName", firstName);
        model.addAttribute("lastName", lastName);
        model.addAttribute("createdOn", createdOn);
        return "profile";
    }

    @GetMapping(value = "/forgot")
    public ModelAndView displayForgotPasswordPage() {
        return new ModelAndView("forgotPassword");
    }

    @PostMapping(value = "/forgot")
    public ModelAndView processForgotPasswordForm(ModelAndView modelAndView, @RequestParam("email") String userEmail, HttpServletRequest request) {
        // Lookup user in database by e-mail
        Optional<User> optional = Optional.ofNullable(userService.findByEmail(userEmail));

        if (!optional.isPresent()) {
            modelAndView.addObject("errorMessage", "We didn't find an account for that e-mail address");
        } else {

            // Generate random 36-character string token for reset password
            User user = optional.get();
            user.setResetToken(UUID.randomUUID().toString());

            // Save token to database
            userService.save(user);

            String appUrl = request.getScheme() + "://" + request.getServerName();

            // Email message
            SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
            passwordResetEmail.setFrom("support@demo.com");
            passwordResetEmail.setTo(user.getEmail());
            passwordResetEmail.setSubject("Password Reset Request");
            passwordResetEmail.setText("To reset your password, click the link below:\n" + appUrl
                    + "/reset?token=" + user.getResetToken());

            emailService.sendEmail(passwordResetEmail);

            // Add success message to view
            modelAndView.addObject("successMessage", userEmail);
        }

        modelAndView.setViewName("forgotPassword");
        return modelAndView;

    }

    @GetMapping(value = "/reset")
    public ModelAndView displayResetPasswordPage(ModelAndView modelAndView, @RequestParam("token") String token) {

        Optional<User> user = userService.findUserByResetToken(token);

        if (user.isPresent()) { // Token found in DB
            modelAndView.addObject("resetToken", token);
        } else { // Token not found in DB
            modelAndView.addObject("errorMessage", "Oops!  This is an invalid password reset link");
        }
        modelAndView.addObject("userForm", new User());

        modelAndView.setViewName("resetPassword");
        return modelAndView;
    }

    @PostMapping(value = "/reset")
    public ModelAndView setNewPassword(@ModelAttribute("userForm") @Valid User userForm, BindingResult bindingResult, ModelAndView modelAndView, @RequestParam Map<String, String> requestParams, RedirectAttributes redirect) {
        userForgotPasswordValidator.validate(userForm, bindingResult);

        // Find the user associated with the reset token
        Optional<User> user = userService.findUserByResetToken(requestParams.get("token"));

        if (bindingResult.hasErrors()) {
            System.out.println("Errors " + bindingResult.hasErrors());

            modelAndView.setViewName("resetPassword");
            return modelAndView;
        }

        // This should always be non-null but we check just in case
        if (user.isPresent()) {

            User resetUser = user.get();

            // Set new password
            resetUser.setPassword(requestParams.get("password"));

            // Set the reset token to null so it cannot be used again
            resetUser.setResetToken(null);

            // Save user
            userService.save(resetUser);

            // In order to set a model attribute on a redirect, we must use
            // RedirectAttributes
            redirect.addFlashAttribute("successMessage", "You have successfully reset your password.  You may now login");

            modelAndView.setViewName("redirect:login");
            return modelAndView;

        } else {
            modelAndView.addObject("errorMessage", "Oops!  This is an invalid password reset link");
            modelAndView.setViewName("resetPassword");
        }

        return modelAndView;
    }

    // Going to reset page without a token redirects to login page
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ModelAndView handleMissingParams(MissingServletRequestParameterException ex) {
        return new ModelAndView("redirect:login");
    }
}