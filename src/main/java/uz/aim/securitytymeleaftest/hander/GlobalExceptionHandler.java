package uz.aim.securitytymeleaftest.hander;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;
import uz.aim.securitytymeleaftest.domains.entity.User;
import uz.aim.securitytymeleaftest.dtos.response.ApiResponse;
import uz.aim.securitytymeleaftest.exceptions.GenericConflictException;
import uz.aim.securitytymeleaftest.exceptions.GenericNotFoundException;
import uz.aim.securitytymeleaftest.exceptions.GenericNotMatchesException;
import uz.aim.securitytymeleaftest.exceptions.GenericRuntimeException;

/**
 * @author : Abbosbek Murodov
 * @since : 07/05/23 / 18:27
 * Project : security-tymeleaf-test / IntelliJ IDEA
 */

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(GenericNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView notFound(GenericNotFoundException e) {
        ModelAndView mav = new ModelAndView("error/404");
        mav.addObject("error", e.getMessage());
        return mav;
    }

    @ExceptionHandler(GenericConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ModelAndView conflict(GenericConflictException e) {
        ModelAndView mav = new ModelAndView("error/403");
        mav.addObject("error", e.getMessage());
        return mav;
    }

    @ExceptionHandler(GenericNotMatchesException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ModelAndView notMatches(GenericNotMatchesException e) {
        ModelAndView mav = new ModelAndView("error/409");
        mav.addObject("error", e.getMessage());
        return mav;
    }

    @ExceptionHandler(GenericRuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ModelAndView runtime(GenericRuntimeException e) {
        ModelAndView mav = new ModelAndView("error/400");
        mav.addObject("error", e.getMessage());
        return mav;
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ModelAndView badCredentials(UsernameNotFoundException e) {
        ModelAndView mav = new ModelAndView("error/400");
        mav.addObject("error", e.getMessage());
        return mav;
    }

}
