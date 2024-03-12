package co.kr.adcapsule.apt.config.handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class UserLoginSuccessCustomHandler implements AuthenticationSuccessHandler {
    public UserLoginSuccessCustomHandler() {
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authResult) {
        //login success
        log.info("##login is success##");
    }
}
