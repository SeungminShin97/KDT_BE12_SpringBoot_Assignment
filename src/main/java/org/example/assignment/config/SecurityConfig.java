package org.example.assignment.config;

import lombok.RequiredArgsConstructor;
import org.example.assignment.domain.enums.user.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationSuccessHandler CustomLoginSuccessHandler;
    private final AuthenticationFailureHandler CustomLoginFailureHandler;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/admin").hasAuthority(Role.ROLE_ADMIN.getAuthority())
                        .anyRequest().permitAll())
                .formLogin(form -> form
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .successHandler(CustomLoginSuccessHandler)
                        .failureHandler(CustomLoginFailureHandler)
                        .permitAll())
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID"))
                .sessionManagement(sessionManagement -> sessionManagement
                        .maximumSessions(1)
                        .maxSessionsPreventsLogin(true)).build();
    }
}
