package be.ap.birde_observe_app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
            .csrf(CsrfConfigurer::disable)
            .authorizeHttpRequests(authorizationRegistry -> authorizationRegistry
                .requestMatchers(HttpMethod.GET, "/", "/*.html").permitAll()
                .requestMatchers("/js/**", "/css/**", "/images/**", "/test/**").permitAll()
                // .requestMatchers("/user/login", "/user/register", "/test/**").permitAll()
                // .requestMatchers("/login").permitAll()
                .requestMatchers(HttpMethod.OPTIONS).permitAll()
                .anyRequest().authenticated()
            )
            .formLogin((form) -> form
                .loginPage("/login.html")
                .loginProcessingUrl("/doLogin")
                .defaultSuccessUrl("/hello", true)
                .usernameParameter("name")
                .passwordParameter("passwd")
                
                .failureForwardUrl("/login/error")
                .permitAll()
            )
            .logout((logout) -> 
                logout.logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .deleteCookies()
                .clearAuthentication(true)
                .invalidateHttpSession(true)                
            );

        httpSecurity.exceptionHandling((exceptionConfigurer) -> exceptionConfigurer
            .accessDeniedPage("/403.html")
        );

        return httpSecurity.build();
    }


    
}
