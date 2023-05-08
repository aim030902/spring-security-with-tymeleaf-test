package uz.aim.securitytymeleaftest.configs.security.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.aim.securitytymeleaftest.domains.entity.User;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;

/**
 * @author : Abbosbek Murodov
 * @since : 07/05/23 / 18:44
 * Project : security-tymeleaf-test / IntelliJ IDEA
 */

public record UserDetail(User user) implements UserDetails {
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        if (Objects.nonNull(user.getRoles())) {
            user.getRoles().forEach(
                    role -> {
                        if (Objects.nonNull(role.getPermissions())) {
                            authorities.add(role);
                            authorities.addAll(role.getPermissions());
                        }
                    }
            );
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return user.isActive();
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.isActive();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return user.isActive();
    }

    @Override
    public boolean isEnabled() {
        return user.isActive();
    }

    private static final Function<String, SimpleGrantedAuthority> authority = SimpleGrantedAuthority::new;

}
