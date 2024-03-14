package co.kr.adcapsule.apt.config.handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class UserLoginFailureCustomHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) {
        //login failure
        log.info("##login is failure##");
        String failMessage = "";

        // [STEP.2] 발생한 Exception에 대해서 확인한다.
        if (failed instanceof BadCredentialsException) {
            failMessage = "로그인 정보가 일치하지 않습니다.";
            //Login Fail Count ++
            //5회 이상 실패 시 계정 "L"  잠김 상태 처리
        } else if (failed instanceof LockedException) {
            failMessage = "계정이 잠겨 있습니다.";
        } else if (failed instanceof DisabledException) {
            failMessage = "계정이 비활성화되었습니다.";
        } else if (failed instanceof AccountExpiredException) {
            failMessage = "계정이 만료되었습니다.";
        } else if (failed instanceof CredentialsExpiredException) {
            failMessage = "인증 정보가 만료되었습니다.";
        }

        log.info("result fail Msg >> " + failMessage);
        log.info("result fail Msg >> " + failMessage);
        //fail cnt ++

        //이후 혹시나 가지고 잇는 session도 삭제처리



    }
}
