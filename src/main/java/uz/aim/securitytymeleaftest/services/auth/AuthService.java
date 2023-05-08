package uz.aim.securitytymeleaftest.services.auth;

import lombok.NonNull;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.aim.securitytymeleaftest.configs.security.auth.AuthManager;
import uz.aim.securitytymeleaftest.configs.security.user.UserDetail;
import uz.aim.securitytymeleaftest.domains.entity.ActivationCode;
import uz.aim.securitytymeleaftest.domains.entity.User;
import uz.aim.securitytymeleaftest.domains.enums.Status;
import uz.aim.securitytymeleaftest.dtos.auth.LoginDTO;
import uz.aim.securitytymeleaftest.dtos.auth.RegisterDTO;
import uz.aim.securitytymeleaftest.dtos.response.ApiResponse;
import uz.aim.securitytymeleaftest.dtos.user.UserDTO;
import uz.aim.securitytymeleaftest.exceptions.GenericNotFoundException;
import uz.aim.securitytymeleaftest.exceptions.GenericRuntimeException;
import uz.aim.securitytymeleaftest.mapper.UserMapper;
import uz.aim.securitytymeleaftest.repository.UserRepository;
import uz.aim.securitytymeleaftest.services.mail.MailService;
import uz.aim.securitytymeleaftest.validation.AuthValidation;

import java.time.LocalDateTime;

/**
 * @author : Abbosbek Murodov
 * @since : 07/05/23 / 18:14
 * Project : security-tymeleaf-test / IntelliJ IDEA
 */

@Service
public class AuthService {
    @Value("${activation.link.base.path}")
    private String basePath;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthValidation authValidation;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ActivationCodeService activationCodeService;
    @Autowired
    private MailService mailService;
    @Autowired
    private AuthManager authManager;

    @Transactional
    @SneakyThrows
    public ApiResponse<User> register(@NonNull RegisterDTO dto) {
        authValidation.validateOnRegister(dto);
        User createUser = userMapper.toEntity(dto);
        User savedUser = userRepository.save(createUser);
        UserDTO userDTO = userMapper.toDTO(savedUser);
        ActivationCode activationCode = activationCodeService.generateCode(userDTO);
        String link = basePath.formatted(activationCode.getActivationLink());
        mailService.sendEmail(userDTO, link);
        return new ApiResponse<>("User successfully registered, please confirm email!", savedUser, true);
    }


    public ApiResponse<User> login(@NonNull LoginDTO dto) {
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(dto.username(), dto.password()));
        UserDetail userDetail = (UserDetail) authentication.getPrincipal();
        return new ApiResponse<>("User successfully login", userDetail.user(), true);
    }

    @Transactional(noRollbackFor = GenericRuntimeException.class)
    public Boolean activateUser(String activationCode) {
        ActivationCode activationLink = activationCodeService.findByActivationLink(activationCode);
        if (activationLink.getValidTill().isBefore(LocalDateTime.now())) {
            activationCodeService.delete(activationLink.getId());
            throw new GenericRuntimeException("Activation Code is not active");
        }
        User authUser = userRepository.findById(activationLink.getUserId()).orElseThrow(() -> {
            throw new GenericNotFoundException("User not found");
        });

        authUser.setStatus(Status.ACTIVE);
        userRepository.save(authUser);
        return true;
    }

    public User getCurrentAuthUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

        return userRepository.findByUsername(username).orElseThrow(() -> new GenericNotFoundException("User not found!"));
    }

}
