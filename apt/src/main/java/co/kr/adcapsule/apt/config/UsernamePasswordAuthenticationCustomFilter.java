package co.kr.adcapsule.apt.config;

import co.kr.adcapsule.apt.config.handler.UserLoginFailureCustomHandler;
import co.kr.adcapsule.apt.config.handler.UserLoginSuccessCustomHandler;
import co.kr.adcapsule.apt.login.dto.UserCreateDto;
import co.kr.adcapsule.apt.login.dto.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;


//User Authentication Filter
@RequiredArgsConstructor
public class UsernamePasswordAuthenticationCustomFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final ObjectMapper objectMapper;
    private final UserLoginSuccessCustomHandler successHandler;
    private final UserLoginFailureCustomHandler failureHandler;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        //1. body 에서 로그인 정보 받아오기
        UserCreateDto loginDto = null;

        try {
            loginDto = objectMapper.readValue(request.getInputStream(), UserCreateDto.class);
        } catch (IOException e) {
            throw new RuntimeException("Internal server error :  Login Dto Setting error" );
        }

        //2. Login Id, Password 를 기반으로 AuthenticationToken 생성
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getLoginId() , loginDto.getPassword());

        //3. User Password 인증이 이루어지는 부분
        // 상위 클래스 타고타고 ..Provider 거쳐서 쭈욱 가다보면 내부 메소드 retrieveUser()에서 loadUserByUserName 호출함.
        //"authenticate" 가 실행될때 "PrincipalDetailService.loadUserByUsername" 실행
        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        return authenticate;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        this.successHandler.onAuthenticationSuccess(request, response, authResult);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        this.failureHandler.onAuthenticationFailure(request,response, failed);
    }



}
