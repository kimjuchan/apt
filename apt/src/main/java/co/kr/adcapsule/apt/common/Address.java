package co.kr.adcapsule.apt.common;


import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Address {

    //리 정보는 존재하나 읍면동까지만 표현
    //시도
    private String sido;
    //시군구
    private String gun;
    //읍면동
    private String dong;

}
