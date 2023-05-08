package uz.aim.securitytymeleaftest.configs.security.jobs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import uz.aim.securitytymeleaftest.domains.entity.User;
import uz.aim.securitytymeleaftest.domains.enums.Status;
import uz.aim.securitytymeleaftest.repository.UserRepository;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author : Abbosbek Murodov
 * @since : 07/05/23 / 21:01
 * Project : security-tymeleaf-test / IntelliJ IDEA
 */

@Configuration
@EnableScheduling
public class CronJob {
    @Autowired
    private UserRepository userRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(CronJob.class);

    @Scheduled(fixedRate = 2, timeUnit = TimeUnit.MINUTES)
    public void scheduleFixedDelayTask() {
        List<User> blockedUsers = userRepository.getAllBlocked().orElse(new ArrayList<>());
        blockedUsers.stream().filter(user -> Duration.between(user.getLastLoginTime(), LocalDateTime.now()).getSeconds() >= 120)
                .forEach(user -> {
                    user.setStatus(Status.ACTIVE);
                    user.setLoginTryCount(0);
                    user.setLastLoginTime(null);
                });
        userRepository.saveAll(blockedUsers);
        LOGGER.info("Blocked users are activated!");
    }
}
