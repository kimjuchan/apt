package co.kr.adcapsule.apt.config;


import co.kr.adcapsule.apt.config.handler.UserLoginFailureCustomHandler;
import co.kr.adcapsule.apt.config.handler.UserLoginSuccessCustomHandler;
import co.kr.adcapsule.apt.login.service.CustomUserDetailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 *
 * -Session 방식 동작
 * 1) 최초 클라이언트가 ID, PW로 로그인 요청 시 ➡
 * 2) 서버에서 세션을 만들고 세션 ID를 클라이언트에게 응답 ➡
 * 3) 클라이언트는 로컬 스토리지에 세션 ID를 저장하고 앞으로 서버에게 요청시 세션ID를 같이 실어서 보냄 ➡
 * 4) 서버는 세션ID 확인 후 인증 페이지로 안내함
 *
 *
 */


/**
 * 참조 : https://zks145.tistory.com/m/106
 *
 * SSR(Server Side Rendering) -> 환경에서 jwt 지양하는 이유
 * 1) 성능 저하
 * 2) 보안 취약점 -> 토큰 자체 값만 유출되면 보안 위험
 * -->MPA 개발 시 많이 사용됨 (새로운 페이지를 요청할 때마다 정적 리소스가 다운로드되는 형태)
 * 처음 속도는 빠르겠지만 페이지 이동 시 불필요한 데이터들도 다시 가져오게되는 상황 발생.
 *
 * CSR(Client Side Rendering) -> SPA 개발 시 주로 사용. 모든 정적 리소스를 최초 한 번에 다운로드
 * 그 이후 새로운 페이지 요청 시 페이지 갱신에 필요한 데이터만 전달 받아서 페이지 갱신 형태.
 *
 *
 *  JWT 활용 인증 인가 처리 x
 *  -> SESSION 사용 시 서버 메모리에 해당 SESSION_ID 저장구조  사용자가 많아질경우 서버에 부하 발생.
 *  -> 서버 메모리 확보 필요 없음.
 *
 *  원래 기존에는 jwt 형식으로 인증 인가 처리를 진행하려고 했는데 위에 내용을 알게된 후 다시 기존 session에 저장하는 형식으로 개발 진행 예정.
 *
 * ->추가적으로 중요한 부분
 * 1) REST API의 필요 요소 중 하나는 ****무상태성***** 제공해야하기 때문에  JWT 인증 방식을 채택하게 되는거임.
 * -> 쿠키 세션 방식으로 진행한다면   필요 요소인 무상태성을 보장 안함.
 *
 *  무상태성 (STATELESS) : 서버가 클라이언트의 상태를 관리하지 않는 인증 방식
 *  > 동작 방식
 *  - 서버에서 토큰 발급 후 클라이언트에게 전달.
 *  - 클라이언트는 이 토큰을 통해 모든 요청에 포함시켜 서버에 전달
 *  - 서버는 토큰을 통해 사용자 인증 및 요청 처리
 *
 *  (서버는 상태를 유지하지 않기 때문에 확장이 용이 + 부하 분산 간단)
 *  (토큰이 탈취 된다면 보안에 아주 위협이됨)
 *
 *  상태성(STATEFUL) : 서버가 클라이언트의 상태를 유지 및 관리하는 인증 방식
 *  > 동작 방식
 *  - 서버에 세션을 생성 , 세선 ID 클라이언트에게 전달
 *  - 클라이언트는 세션 ID를 모든 요청에 포함시켜 서버에 전달.
 *  - 서버는 받은 세션 ID를 참조하여 사용자 인증 및 요청 처리.
 *
 *  (보안은 더 좋음. 상태 값을 서버에 유지 및 관리해야하기 때문에 사용자가 많아질 수록 서버 부하가 증가됨 + 확장이 어려움)
 *
 * -----요약----------
 * SSR + REST API 사용 하지 않기 + 세션 쿠키 or OAuth2 로그인 방식 사용(CSRF 방어 필요, 세션 사용 시 클러스터링 등의 이슈 고려)
 * CSR + REST API 사용 + JWT(CSRF 방어 불필요, 세션 클러스터링 불필요)
 *
 *  -->만약 rest api 형식에서
 *
 *
 *  현재 기준은 SSR 방식 + SESSION 기반으로 만들기
 *  추후에 REACT.JS OR VUE.JS 프론트 서버 따로 만드는 형식으로 진행할때 (CRS)  + JWT 활용하여 인증 인가 처리 서비스 구현.
 */
@EnableWebSecurity
@RequiredArgsConstructor
@Configuration
public class SecurityConfig {

    //1) CORS  설정
    //2) CSRF 설정.
    //3) formLogin()설정
    //4) customEntryPoint 설정.

    private final ObjectMapper objectMapper;
    private final UserLoginSuccessCustomHandler successHandler;
    private final UserLoginFailureCustomHandler failureHandler;
    private final CustomUserDetailService customUserDetailService;

    private static final int ONE_MONTH = 2678400;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);

        CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();
        requestHandler.setCsrfRequestAttributeName("_csrf");
        http
                .csrf(csrf -> csrf
                        .csrfTokenRequestHandler(requestHandler)
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                        .ignoringRequestMatchers("/", "/user/login/**", "/user/logout/**")
                )

                //url 접근 권한 설정.
                .authorizeHttpRequests(authorizeRequest  -> authorizeRequest
                        .requestMatchers("/user/sign-in").permitAll()
                        .requestMatchers("/user/sign-up").permitAll()
                        .requestMatchers("/user/login").permitAll()
                        .requestMatchers("/user/create").permitAll()
                        .requestMatchers("/css/**","/images/**","/js/**").permitAll()
                        .anyRequest().hasRole("USER")
                )
                //자체적으로 선언 시 기본 UsernamePasswordAuthenticationFilter를 통해서 폼 기반 로그인 처리.

                .formLogin(login -> login
                        .loginPage("/user/login")	// [A] 커스텀 로그인 페이지 지정
                        .loginProcessingUrl("/user/login-processing")	// [B] submit 받을 url
                        .usernameParameter("loginId")	// [C] submit할 아이디
                        .passwordParameter("password")	// [D] submit할 비밀번호
                        .successHandler(successHandler)
                        .failureHandler(failureHandler)

                        .permitAll()
                )
                .addFilter(new UsernamePasswordAuthenticationCustomFilter(authenticationManager, objectMapper , successHandler, failureHandler))
               /* .rememberMe(customizer -> customizer
                        .rememberMeParameter("remember-me")
                        .tokenValiditySeconds(ONE_MONTH)
                        .userDetailsService(customUserDetailService)
                        .authenticationSuccessHandler(successHandler)
                )*/

                .logout(withDefaults())
        ;
        return http.build();
    }



    public class MyCustomDsl extends AbstractHttpConfigurer<MyCustomDsl, HttpSecurity> {

        @Override
        public void configure(HttpSecurity http)  {
            AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
            //http.addFilter(config.corsFilter()); //스프링 시큐리티 필터내에 cors 관련 필터가 있음!! 그래서 제공해주는 필터 객체를 생성후 HttpSecurity에 등록!
            http.addFilter(new UsernamePasswordAuthenticationCustomFilter(authenticationManager, objectMapper , successHandler, failureHandler));
        }
    }














}
