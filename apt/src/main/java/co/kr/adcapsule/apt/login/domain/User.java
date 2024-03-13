package co.kr.adcapsule.apt.login.domain;

import co.kr.adcapsule.apt.common.Address;
import co.kr.adcapsule.apt.common.BaseEntity;
import co.kr.adcapsule.apt.common.enums.GenderType;
import co.kr.adcapsule.apt.common.enums.UserRoleType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long id;

    private String loginId;
    private String password;

    //추후에 OAuth 연동한다면 sns userNickname 가져와 저장할 변수
    private String userNickname;
    private String email;
    private String userName;
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private GenderType genderType;

    @Enumerated(EnumType.STRING)
    private UserRoleType roleType;

    private Long failCount;


    //정보
    @Embedded
    private Address address;

}
