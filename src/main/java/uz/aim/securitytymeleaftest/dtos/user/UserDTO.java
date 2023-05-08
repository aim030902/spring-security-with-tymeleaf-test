package uz.aim.securitytymeleaftest.dtos.user;

import uz.aim.securitytymeleaftest.domains.enums.Role;
import uz.aim.securitytymeleaftest.domains.enums.Status;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * @author : Abbosbek Murodov
 * @since : 07/05/23 / 18:08
 * Project : security-tymeleaf-test / IntelliJ IDEA
 */

public record UserDTO(Long id, String fullName, String username, String password, String email, Set<Role> roles, Status status, int loginTryCount, LocalDateTime lastLoginTime, boolean deleted) {
}
