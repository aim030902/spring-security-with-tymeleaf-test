package uz.aim.securitytymeleaftest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.aim.securitytymeleaftest.domains.entity.User;

import java.util.List;
import java.util.Optional;

/**
 * @author : Abbosbek Murodov
 * @since : 07/05/23 / 18:12
 * Project : security-tymeleaf-test / IntelliJ IDEA
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

    Optional<User> findByUsername(String username);
    @Query(value = "from User where status = 'BLOCKED'")
    Optional<List<User>> getAllBlocked();
}
