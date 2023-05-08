package uz.aim.securitytymeleaftest.domains.entity;

import lombok.*;
import uz.aim.securitytymeleaftest.domains.enums.Role;
import uz.aim.securitytymeleaftest.domains.enums.Status;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * @author : Abbosbek Murodov
 * @since : 07/05/23 / 17:45
 * Project : security-tymeleaf-test / IntelliJ IDEA
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String fullName;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false, unique = true)
    private String email;
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Set<Role> roles = new HashSet<>(Set.of(Role.ROLE_USER));
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Status status = Status.NOT_ACTIVE;
    private int loginTryCount;
    private LocalDateTime lastLoginTime;
    private boolean deleted;

    public boolean isActive() {
        return this.status.equals(Status.ACTIVE);
    }

}
