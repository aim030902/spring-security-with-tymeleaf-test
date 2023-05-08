package uz.aim.securitytymeleaftest.configs.security.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.aim.securitytymeleaftest.domains.entity.User;
import uz.aim.securitytymeleaftest.repository.UserRepository;

/**
 * @author : Abbosbek Murodov
 * @since : 07/05/23 / 18:41
 * Project : security-tymeleaf-test / IntelliJ IDEA
 */

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Bad credentials"));
        return new UserDetail(user);
    }
}
