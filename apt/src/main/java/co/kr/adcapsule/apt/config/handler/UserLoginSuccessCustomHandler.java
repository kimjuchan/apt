package co.kr.adcapsule.apt.config.handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;


@Slf4j
@Component
public class UserLoginSuccessCustomHandler implements AuthenticationSuccessHandler {
    public UserLoginSuccessCustomHandler() {
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authResult) throws IOException {
        // IP, 세션 ID
        WebAuthenticationDetails web = (WebAuthenticationDetails) authResult.getDetails();
        System.out.println("IP: " + web.getRemoteAddress());
        System.out.println("Session ID: " + web.getSessionId());

        // 인증 ID
        System.out.println("ID: " + authResult.getName());

        //login success
        log.info("##LOGIN INFO##");
        String url = request.getContextPath();
        log.info("URI > " + request.getRequestURI());
        log.info("URL > " + request.getRequestURL());
        log.info("PATH > " + request.getContextPath());

        response.sendRedirect("/main/index");
        //로그인 실패 cnt 초기화

    }
}
