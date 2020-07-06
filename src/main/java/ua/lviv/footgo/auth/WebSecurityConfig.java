package ua.lviv.footgo.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ua.lviv.footgo.auth.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailsServiceImpl userDetailsServiceImpl;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

/*        @Qualifier("userDetailsServiceImpl")
        @Autowired
        private UserDetailsService userDetailsService;*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/registration").not().fullyAuthenticated()
/*                    .anyRequest()
                    .authenticated()*/
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/api/**").hasRole("ADMIN")
                .antMatchers("/user/**").hasRole("USER")
                .antMatchers("/profile/**").authenticated()
                .antMatchers("/**", "/resources/**", "/forgot", "/reset").permitAll()
                .anyRequest().authenticated()

                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
/*                    .failureUrl("/userLogin?error=loginError")*/
                .defaultSuccessUrl("/", true).permitAll()

                .and()
                .logout()
/*                    .logoutUrl("/user_logout")
                    .logoutSuccessUrl("/protectedLinks")
                    .deleteCookies("JSESSIONID")*/
                .permitAll()
                .logoutSuccessUrl("/login")

/*                    .and()
                    .exceptionHandling()
                    .accessDeniedPage("/403")*/

                .and()
                .csrf().disable();
    }

    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(bCryptPasswordEncoder());
    }

}
