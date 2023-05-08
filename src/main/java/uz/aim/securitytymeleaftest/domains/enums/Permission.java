package uz.aim.securitytymeleaftest.domains.enums;

import org.springframework.security.core.GrantedAuthority;

/**
 *  @author : Abbosbek Murodov
 *  @since  : 07/05/23 / 18:49
 *  Project : security-tymeleaf-test / IntelliJ IDEA
*/

public enum Permission implements GrantedAuthority {
    ;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
