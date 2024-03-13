package co.kr.adcapsule.apt.login.controller;


import co.kr.adcapsule.apt.login.domain.User;
import co.kr.adcapsule.apt.login.dto.UserCreateDto;
import co.kr.adcapsule.apt.login.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;


@Slf4j
@Controller
@RequestMapping("/user")
public class LoginController {
    private final UserServiceImpl userService;

    public LoginController(UserServiceImpl userService) {
        this.userService = userService;
    }


    //회원가입  view
    @GetMapping("/sign-up")
    public ModelAndView signUpUser(ModelMap modelMap){
        return new ModelAndView("./user/sign-up", modelMap);
    }

    @PostMapping("/create")
    public String createUser(UserCreateDto userCreateDto){
        //duplicate check
        userService.dupChkByLoginId(userCreateDto);
        return "redirect:/user/login";
    }

    @GetMapping(value="/login")
    public ModelAndView loginView(ModelMap modelMap){
        return new ModelAndView("./login", modelMap);
    }


}
