package uz.aim.securitytymeleaftest.configs.encryption;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author : Abbosbek Murodov
 * @since : 07/05/23 / 18:26
 * Project : security-tymeleaf-test / IntelliJ IDEA
 */

@Component
public class Encoder {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
