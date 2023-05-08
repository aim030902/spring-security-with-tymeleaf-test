package uz.aim.securitytymeleaftest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.aim.securitytymeleaftest.domains.entity.ActivationCode;

import java.util.Optional;

/**
 * @author : Abbosbek Murodov
 * @since : 02/04/23 / 11:25
 * Project : Trello-REST-API / IntelliJ IDEA
 */

@Repository
public interface ActivationCodeRepository extends JpaRepository<ActivationCode, Long> {
    Optional<ActivationCode> findByActivationLink(String link);
}