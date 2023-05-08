package uz.aim.securitytymeleaftest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author : Abbosbek Murodov
 * @since : 08/05/23 / 11:32
 * Project : security-tymeleaf-test / IntelliJ IDEA
 */

@Controller
public class HomeController {
    @GetMapping("/")
    public ModelAndView indexPage() {
        ModelAndView mav = new ModelAndView("index");
        return mav;
    }

    @GetMapping("/api/home")
    public ModelAndView homePage() {
        ModelAndView mav = new ModelAndView("home");
        return mav;
    }
}
