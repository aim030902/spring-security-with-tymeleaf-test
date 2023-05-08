package uz.aim.securitytymeleaftest.dtos.auth;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

/**
 * @author : Abbosbek Murodov
 * @since : 07/05/23 / 17:57
 * Project : security-tymeleaf-test / IntelliJ IDEA
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RegisterDTO {
    @NotNull(message = "Full Name can not be null !")
    private String fullName;
    @NotNull(message = "Full Name can not be null !")
    private String username;
    @NotNull(message = "Full Name can not be null !")
    private String password;
    @NotNull(message = "Full Name can not be null !")
    private String confirmPassword;
    @Email(message = "Email failed !")
    @NotNull(message = "Email can not be null !")
    private String email;
}
