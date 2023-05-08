package uz.aim.securitytymeleaftest.configs.security.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import uz.aim.securitytymeleaftest.configs.security.auth.AuthEntryPoint;
import uz.aim.securitytymeleaftest.services.oauth2.OAuth2UserService;

import java.util.List;


/**
 * @author : Abbosbek Murodov
 * @since : 02/04/23 / 12:23
 * Project : Trello-REST-API / IntelliJ IDEA
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true
)
public class SecurityConfigurer {

//    @Autowired
//    private AuthEntryPoint authEntryPoint;
    @Autowired
    private OAuth2UserService oAuth2UserService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .cors(httpSecurityCorsConfigurer ->
                        httpSecurityCorsConfigurer.configurationSource(corsConfiguration())
                )
                .authorizeRequests(expressionInterceptUrlRegistry -> expressionInterceptUrlRegistry
                        .antMatchers(SecurityConstant.WHITE_LIST).permitAll()
                        .anyRequest().authenticated()
                )
                .exceptionHandling().accessDeniedPage("/api/auth/login");

        http.oauth2Login()
                .loginPage("/api/auth/login")
                .userInfoEndpoint()
                .userService(oAuth2UserService)
                .and()
                .defaultSuccessUrl("/api/home")
                .and()
                .logout().logoutSuccessUrl("/").permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/api/error/403");

        http.rememberMe()
                .key("RememberMeSecretKey123456789!@#$%^&")
                .rememberMeParameter("remember-me")
                .tokenValiditySeconds(10 * 60);

        http.logout()
                .logoutUrl("/api/auth/logout")
                .logoutSuccessUrl("/api/auth/login")
                .clearAuthentication(true)
                .deleteCookies("JSESSIONID", "remember-me");

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfiguration() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(false);
        config.setAllowedOrigins(List.of("*"));
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}


