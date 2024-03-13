package co.kr.adcapsule.apt.common.enums;


import lombok.Getter;

@Getter
public enum UserStatusType {
    //정상 상태
    A("ACTIVE"),
    //비밀번호 5회 틀릴경우 잠김 상태
    L("LOCK"),
    //휴면 상태
    I("IDLE"),
    //탈퇴 상태
    D("DISABLE");

    private String user_status;

    UserStatusType(String user_status) {
        this.user_status = user_status;
    }
}
