package co.kr.adcapsule.apt.common.enums;


import lombok.Getter;

@Getter
public enum GenderType {

    M("MAN"),
    Y("WOMAN");

    private String genderName;

    GenderType(String genderName) {
        this.genderName = genderName;
    }
}
