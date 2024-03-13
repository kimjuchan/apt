package co.kr.adcapsule.apt.login.service;


import co.kr.adcapsule.apt.login.domain.User;
import co.kr.adcapsule.apt.login.dto.UserCreateDto;
import co.kr.adcapsule.apt.login.dto.UserDto;
import co.kr.adcapsule.apt.login.mapper.UserMapper;
import co.kr.adcapsule.apt.login.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Long createUser(UserCreateDto userCreateDto){

        //pwd encode
        userCreateDto.setPassword(passwordEncoder.encode(userCreateDto.getPassword()));
        //Mapsturct  UserDto to User type change
        User user = UserMapper.INSTANCE.UserCreateRequestToEntity(userCreateDto);
        //exception 처리
        Long userId = Optional.of(userRepository.save(user).getId()).orElseThrow(() -> new RuntimeException("User save error"));

        return userId;
    }

    public void dupChkByLoginId(UserCreateDto userCreateDto){
        Boolean dupChk = userRepository.existsByLoginId(userCreateDto.getLoginId());
        if(!dupChk){
            createUser(userCreateDto);
        }else{
            throw new RuntimeException("이미 가입된 userId 입니다.");
        }
    }

}
