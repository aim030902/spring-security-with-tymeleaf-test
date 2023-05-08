package uz.aim.securitytymeleaftest.configs.security.web;

/**
 * @author : Abbosbek Murodov
 * @since : 02/04/23 / 11:57
 * Project : Trello-REST-API / IntelliJ IDEA
 */

public class SecurityConstant {
    public static final String[] WHITE_LIST = {
            "/api/auth/**",
            "/",
            "/api/error/**"
    };
}
