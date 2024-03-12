package co.kr.adcapsule.apt.login.dto;

import co.kr.adcapsule.apt.common.Address;
import co.kr.adcapsule.apt.common.enums.GenderType;
import co.kr.adcapsule.apt.common.enums.UserRoleType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserDto {


    private String loginId;

    private String email;
    private String userName;
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private GenderType genderType;

    @Enumerated(EnumType.STRING)
    private UserRoleType roleType;

    private Address address;

    //추후에 OAuth 연동한다면 sns userNickname 가져와 저장할 변수
    private String userNickname;
}
