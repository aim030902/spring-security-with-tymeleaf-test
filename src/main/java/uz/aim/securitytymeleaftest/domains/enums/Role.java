package uz.aim.securitytymeleaftest.domains.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import java.util.Set;

/**
 * @author : Abbosbek Murodov
 * @since : 07/05/23 / 17:48
 * Project : security-tymeleaf-test / IntelliJ IDEA
 */

@AllArgsConstructor
@Getter
public enum Role implements GrantedAuthority {
    ROLE_ADMIN(Set.of(Permission.values())),
    ROLE_USER(Set.of(Permission.values()));

    final Set<Permission> permissions;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
