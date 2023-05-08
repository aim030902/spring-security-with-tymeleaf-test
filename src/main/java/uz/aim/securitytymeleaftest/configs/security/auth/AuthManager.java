package uz.aim.securitytymeleaftest.configs.security.auth;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.aim.securitytymeleaftest.configs.security.user.UserDetail;
import uz.aim.securitytymeleaftest.configs.security.user.UserService;
import uz.aim.securitytymeleaftest.domains.entity.User;
import uz.aim.securitytymeleaftest.domains.enums.Status;
import uz.aim.securitytymeleaftest.exceptions.GenericNotFoundException;
import uz.aim.securitytymeleaftest.exceptions.GenericRuntimeException;
import uz.aim.securitytymeleaftest.repository.UserRepository;

import java.time.LocalDateTime;

/**
 * @author : Abbosbek Murodov
 * @since : 07/05/23 / 18:38
 * Project : security-tymeleaf-test / IntelliJ IDEA
 */

@Component
public class AuthManager implements AuthenticationProvider {
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private UserRepository repository;
    @Autowired
    private UserService userService;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        UserDetail userDetails = (UserDetail) userService.loadUserByUsername(username);
        User authUser = userDetails.user();
        if (!encoder.matches(password, authUser.getPassword())) {
            String message;
            authUser.setLoginTryCount(authUser.getLoginTryCount() + 1);
            if (authUser.getLoginTryCount() == 3) {
                message = "You are blocked! Try 2 minutes later!";
                authUser.setStatus(Status.BLOCKED);
                authUser.setLastLoginTime(LocalDateTime.now());
            } else {
                message = "Invalid username or password!";
            }
            repository.save(authUser);
            throw new GenericRuntimeException(message);
        }
        return new UsernamePasswordAuthenticationToken(new UserDetail(authUser), authentication);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
