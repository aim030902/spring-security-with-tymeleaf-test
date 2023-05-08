package uz.aim.securitytymeleaftest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import uz.aim.securitytymeleaftest.domains.entity.User;
import uz.aim.securitytymeleaftest.dtos.auth.LoginDTO;
import uz.aim.securitytymeleaftest.dtos.auth.RegisterDTO;
import uz.aim.securitytymeleaftest.dtos.response.ApiResponse;
import uz.aim.securitytymeleaftest.services.auth.AuthService;
import uz.aim.securitytymeleaftest.services.recaptcha.ReCaptchaService;

import javax.validation.Valid;

/**
 * @author : Abbosbek Murodov
 * @since : 08/05/23 / 11:31
 * Project : security-tymeleaf-test / IntelliJ IDEA
 */

@Controller
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private ReCaptchaService reCaptchaService;
    @Autowired
    private AuthService userService;

    @GetMapping
    public ModelAndView homePage() {
        ModelAndView mav = new ModelAndView("auth/home");
        return mav;
    }

    @GetMapping("/login")
    public ModelAndView loginPage() {
        ModelAndView mav = new ModelAndView("auth/login");
        return mav;
    }

    @GetMapping("/register")
    public ModelAndView registerPage() {
        ModelAndView mav = new ModelAndView("auth/register");
        return mav;
    }
    @GetMapping("/verify")
    public ModelAndView verifyPage() {
        ModelAndView mav = new ModelAndView("auth/verify");
        return mav;
    }



    @PostMapping("/login")
    public String login(@Valid @ModelAttribute LoginDTO dto,  @RequestParam(name="g-recaptcha-response") String captcha) {
        if(reCaptchaService.validateCaptcha(captcha))
        {
            ApiResponse<User> apiResponse = userService.login(dto);
            if (apiResponse.isSuccess()) {
                return "redirect:/api/home";
            }
            return "redirect:/api/error/409";
        }
        return "redirect:/api/error/403";

    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute RegisterDTO dto, @RequestParam(name="g-recaptcha-response") String captcha) {
        if(reCaptchaService.validateCaptcha(captcha))
        {
            ApiResponse<User> apiResponse = userService.register(dto);
            if (apiResponse.isSuccess()) {
                return "redirect:/api/auth/verify";
            }
            return "redirect:/api/error/409";
        }
        return "redirect:/api/error/403";

    }

    @GetMapping("/activate")
    public String activate(@RequestParam(name = "activation_code") String activationCode) {
        if (userService.activateUser(activationCode)) {
            return "redirect:/api/auth/login";
        }
        return "redirect:/api/error/404";
    }
}
