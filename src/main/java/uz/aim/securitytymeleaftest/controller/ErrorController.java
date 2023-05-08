package uz.aim.securitytymeleaftest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author : Abbosbek Murodov
 * @since : 08/05/23 / 11:31
 * Project : security-tymeleaf-test / IntelliJ IDEA
 */

@Controller
@RequestMapping("/api/error")
public class ErrorController {

    @GetMapping("/400")
    public ModelAndView error400() {
        ModelAndView mav = new ModelAndView("error/400");
        return mav;
    }

    @GetMapping("/403")
    public ModelAndView error403() {
        ModelAndView mav = new ModelAndView("error/403");
        return mav;
    }

    @GetMapping("/404")
    public ModelAndView error404() {
        ModelAndView mav = new ModelAndView("error/404");
        return mav;
    }

    @GetMapping("/409")
    public ModelAndView error409() {
        ModelAndView mav = new ModelAndView("error/409");
        return mav;
    }
}
