package ru.fruzbuka.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import ru.fruzbuka.persist.repo.UserRepository;

import javax.servlet.http.HttpServletResponse;

//@EnableWebSecurity(debug = true)
@EnableWebSecurity
@PropertySource("classpath:application.properties")
public class SecurityConfiguration {

    @Value("${props.security.username}")
    private String memUserName;

    @Value("${props.security.password}")
    private String memPassword;

    @Autowired
    public void authConfigure(AuthenticationManagerBuilder auth,
                              UserDetailsService userDetailsService,
                              PasswordEncoder passwordEncoder) throws Exception {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        auth.authenticationProvider(provider);

        auth.inMemoryAuthentication()
                .withUser(memUserName)
                .password(passwordEncoder.encode(memPassword))
                .roles("ADMIN");
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return new UserAuthService(userRepository);
    }

    @Configuration
    @Order(2)
    public static class UiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {

        @Autowired
        private AccessDeniedHandler accessDeniedHandler;

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .authorizeRequests()
//                    .antMatchers("/", "/asses_denied").permitAll()
                    .antMatchers("/", "/css/**", "/js/**", "/webfonts/**").permitAll()
                    .antMatchers("/user/**").hasRole("ADMIN")
                    .antMatchers("/product/**").hasAnyRole("ADMIN", "MANAGER")
                    .antMatchers("/login*").anonymous()
                    .anyRequest().authenticated()
                    .and()
                    .formLogin()
                    .loginPage("/login")
                    .and()
                    .exceptionHandling().accessDeniedHandler(accessDeniedHandler);
        }
    }

    //    @Configuration
//    @Order(1)
    public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .antMatcher("/api/**")//todo test
                    .authorizeRequests()
                    .antMatchers("/api/**").hasRole("ADMIN")
                    .and()
                    .httpBasic()
                    .authenticationEntryPoint((req, resp, exception) -> {
                        resp.setContentType("application/json");
                        resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                        resp.setCharacterEncoding("UTF-8");
                        resp.getWriter().println("{ \"error\": \"" + exception.getMessage() + "\" }");
                    })
                    .and()
                    .csrf().disable()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        }
    }

}
