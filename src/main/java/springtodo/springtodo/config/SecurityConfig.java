package springtodo.springtodo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import springtodo.springtodo.services.CustomUserDetailsService;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConf) throws Exception {
        return authConf.getAuthenticationManager();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.httpBasic().and().formLogin().and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"));

        http.authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST, "/todos/**").authenticated()
                .requestMatchers(HttpMethod.DELETE, "/todos/**").authenticated()
                .requestMatchers(HttpMethod.PUT, "/todos/**").authenticated()
                .requestMatchers(HttpMethod.GET,"/hello").authenticated()
                .anyRequest()
                .authenticated();

        http.csrf().disable();
        return http.build();
    }

}
