
package co.kr.adcapsule.apt.login.service;

import co.kr.adcapsule.apt.login.domain.PrincipalDetails;
import co.kr.adcapsule.apt.login.domain.User;
import co.kr.adcapsule.apt.login.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import java.util.Optional;


@Configuration
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login_id) throws UsernameNotFoundException {

        User user = Optional.of(userRepository.findByLoginId(login_id)).orElseThrow(()-> new UsernameNotFoundException("해당 로그인 정보를 다시 확인해주시기 바랍니다."));

        return new PrincipalDetails(user);
    }


}

