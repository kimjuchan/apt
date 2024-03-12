package co.kr.adcapsule.apt.common.enums;


import lombok.Getter;

@Getter
public enum UserStatusType {

    A("ACTIVE"),
    I("IDLE"),
    D("DISABLE");

    private String user_status;

    UserStatusType(String user_status) {
        this.user_status = user_status;
    }
}
