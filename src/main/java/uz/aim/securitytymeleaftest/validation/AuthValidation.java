package uz.aim.securitytymeleaftest.validation;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.aim.securitytymeleaftest.dtos.auth.RegisterDTO;
import uz.aim.securitytymeleaftest.exceptions.GenericConflictException;
import uz.aim.securitytymeleaftest.exceptions.GenericNotMatchesException;
import uz.aim.securitytymeleaftest.repository.UserRepository;

/**
 * @author : Abbosbek Murodov
 * @since : 07/05/23 / 18:17
 * Project : security-tymeleaf-test / IntelliJ IDEA
 */

@Component
public class AuthValidation {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public void validateOnRegister(@NonNull RegisterDTO dto) {
        if (!dto.getPassword().equals(dto.getConfirmPassword())) {
            throw new GenericNotMatchesException("Password not matches !");
        }
        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new GenericConflictException("This is username already exists");
        }
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new GenericConflictException("This is email already exists");
        }
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
    }
}
