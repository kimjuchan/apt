package co.kr.adcapsule.apt.main.controller;



import co.kr.adcapsule.apt.login.domain.PrincipalDetails;
import co.kr.adcapsule.apt.login.domain.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequestMapping("/main")
@Controller
public class MainController {

    @GetMapping("/index")
    public String mainView(Model model, @AuthenticationPrincipal PrincipalDetails userInfo){
        //인증 처리는 완료되었는데 user 정보를 못가져옴

        model.addAttribute("userInfo", userInfo);
        model.addAttribute("loginId", userInfo.getLoginId());
        return "./main/index";
    }

}
