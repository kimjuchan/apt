package co.kr.adcapsule.apt.login.dto;

import co.kr.adcapsule.apt.common.Address;
import co.kr.adcapsule.apt.common.enums.GenderType;
import co.kr.adcapsule.apt.common.enums.UserRoleType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserCreateDto {
    @NotBlank(message = "loginId 정보없음")
    private String loginId;

    @NotBlank(message = "pwd 정보없음")
    @Size(min=4, max = 20, message = "패스워드는 최소 4자리 이상, 20자리 이하로 입력 해주세요")
    @Pattern(regexp = "^[a-z0-9]*$", message = "알파벳 a - z , 숫자 0 -9만 입력 가능")
    private String password;

    @NotBlank(message = "email 정보없음")
    @Email(message = "이메일 형식에 맞게 작성 부탁드립니다")
    private String email;

    @NotBlank(message = "name 정보없음")
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
