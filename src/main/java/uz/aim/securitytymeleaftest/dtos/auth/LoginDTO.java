package uz.aim.securitytymeleaftest.dtos.auth;

import javax.validation.constraints.NotNull;

/**
 * @author : Abbosbek Murodov
 * @since : 07/05/23 / 18:01
 * Project : security-tymeleaf-test / IntelliJ IDEA
 */

public record LoginDTO(
        @NotNull(message = "Username can not be null !")
        String username,
        @NotNull(message = "Password can not be null !")
        String password
) {}
