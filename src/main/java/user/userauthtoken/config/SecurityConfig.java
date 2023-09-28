package user.userauthtoken.config;
import lombok.RequiredArgsConstructor;
import  user.userauthtoken.config.UserAuthenticationProvider;
import  user.userauthtoken.config.UserAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {


    /*

    My main issue is that I'm expecting the application to use jwt and the api/v1/login", "api/vi/register token be permited by default/
    The app use the normal security and when I enter to any url it needs to have the password as the interface is coming which I didn't expect to.

    Here is the resources I tried using/  <<<https://www.bezkoder.com/spring-boot-jwt-authentication/>>>

    Incase You can help Then go ahead... The issue is only in this file when declaring the two objects for provided and entry point for auth.


    When I try to get the two config classes am getting 'Could not autowire. No beans of 'xxxxxxxxxxx' type found.'.

    I also tried creating the constructors without the used of RequiredArgsConstructor but didn't work
     */
    private final UserAuthenticationEntryPoint userAuthenticationEntryPoint;
    private final UserAuthenticationProvider userAuthenticationProvider;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .exceptionHandling(customizer -> customizer.authenticationEntryPoint(userAuthenticationEntryPoint))
                .addFilterBefore(new JwtAuthFilter(userAuthenticationProvider), BasicAuthenticationFilter.class)
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(customizer -> customizer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(HttpMethod.POST, "api/v1/login", "api/vi/register").permitAll()
                        .anyRequest().authenticated())
        ;
        return http.build();
    }
}

