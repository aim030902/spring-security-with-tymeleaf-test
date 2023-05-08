package uz.aim.securitytymeleaftest.exceptions;

/**
 * @author : Abbosbek Murodov
 * @since : 07/05/23 / 18:18
 * Project : security-tymeleaf-test / IntelliJ IDEA
 */

public class GenericNotFoundException extends RuntimeException{
    public GenericNotFoundException(String message) {
        super(message);
    }
}
