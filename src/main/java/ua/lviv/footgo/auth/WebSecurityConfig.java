package ua.lviv.footgo.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

/*    @Bean
    public UserDetailsService userDetailsService() throws Exception {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

        manager.createUser(User
                .withUsername("admin")
                .password(bCryptPasswordEncoder().encode("admin"))
                .roles("ADMIN")
                .build());

        manager.createUser(User
                .withUsername("user")
                .password(bCryptPasswordEncoder().encode("user"))
                .roles("USER")
                .build());

        return manager;
    }*/


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Configuration
    @Order(1)
    public class AdminLoginConfigurationAdapter extends WebSecurityConfigurerAdapter {
        public AdminLoginConfigurationAdapter() {
            super();
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.inMemoryAuthentication()
                    .withUser("admin")
                    .password(bCryptPasswordEncoder().encode("admin"))
                    .roles("ADMIN");
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .antMatchers("/admin/**")
                    .hasRole("ADMIN")
                    .and()
                    .formLogin()
                    .loginPage("/login")
                    .loginProcessingUrl("/admin_login")
                    .failureUrl("/login?error=loginError")
                    .defaultSuccessUrl("/admin")

                    .and()
                    .logout()
                    .logoutUrl("/admin_logout")
                    .logoutSuccessUrl("/admin")
                    .deleteCookies("JSESSIONID")

                    .and()
                    .exceptionHandling()
                    .accessDeniedPage("/403")
                    .and()
                    .csrf().disable();
        }
    }

    @Configuration
    @Order(2)
    public class UserRegistrationConfigurationAdapter extends WebSecurityConfigurerAdapter {
        public UserRegistrationConfigurationAdapter() {
            super();
        }

/*        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.inMemoryAuthentication()
                    .withUser("user")
                    .password(bCryptPasswordEncoder().encode("user"))
                    .roles("USER");
        }*/

        @Qualifier("userDetailsServiceImpl")
        @Autowired
        private UserDetailsService userDetailsService;

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .antMatchers("/resources/**", "/registration").permitAll()
                    .anyRequest()
                    .authenticated()

                    .and()
                    .formLogin()
                    .loginPage("/userLogin")
/*                    .loginProcessingUrl("/user_login")
                    .failureUrl("/userLogin?error=loginError")
                    .defaultSuccessUrl("/")*/
                    .permitAll()

                    .and()
                    .logout()
/*                    .logoutUrl("/user_logout")
                    .logoutSuccessUrl("/protectedLinks")
                    .deleteCookies("JSESSIONID")*/
                    .permitAll()

                    .and()
                    .exceptionHandling()
                    .accessDeniedPage("/403")

                    .and()
                    .csrf().disable();
        }

        @Bean
        public AuthenticationManager customAuthenticationManager() throws Exception {
            return authenticationManager();
        }

        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
        }
    }

}

/*
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() throws Exception {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

        manager.createUser(User
                .withUsername("admin")
                .password(encoder().encode("adminPass"))
                .roles("ADMIN")
                .build());

        return manager;
    }

    @Bean
    public static PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Configuration
    public static class App1ConfigurationAdapter extends WebSecurityConfigurerAdapter {
        public App1ConfigurationAdapter() {
            super();
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .antMatchers("/admin/**")
                    .hasRole("ADMIN")
                    .and()
                    .formLogin()
                    .loginPage("/login")
                    .loginProcessingUrl("/admin_login")
                    .failureUrl("/login?error=loginError")
                    .defaultSuccessUrl("/admin")

                    .and()
                    .logout()
                    .logoutUrl("/admin_logout")
                    .logoutSuccessUrl("/admin")
                    .deleteCookies("JSESSIONID")

                    .and()
                    .exceptionHandling()
                    .accessDeniedPage("/403")
                    .and()
                    .csrf().disable();
        }
    }
}*/
