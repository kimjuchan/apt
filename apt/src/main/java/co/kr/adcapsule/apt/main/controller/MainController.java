package co.kr.adcapsule.apt.main.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/main")
@Controller
public class MainController {

    @GetMapping("/index")
    public String mainView(){
        return "./main/index";
    }

}
