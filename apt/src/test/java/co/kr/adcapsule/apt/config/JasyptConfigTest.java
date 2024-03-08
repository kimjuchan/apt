package co.kr.adcapsule.apt.config;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.iv.RandomIvGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;



@SpringBootTest
class JasyptConfigTest {

    @Test
    public void jasyptEncodingTest(){
        String userName_before = "juchan";
        String userName_after = "";


        String url = "jdbc:mariadb://localhost:3306/apartment";
        String userName = "root";
        String paswword = "1q2w3e4r!@#";

        String key = "adcapsule!@#";

        StandardPBEStringEncryptor pbeEnc = new StandardPBEStringEncryptor();
        pbeEnc.setAlgorithm("PBEWITHHMACSHA512ANDAES_256");
        pbeEnc.setPassword(key);
        pbeEnc.setIvGenerator(new RandomIvGenerator());

        url = pbeEnc.encrypt(url);
        userName =pbeEnc.encrypt(userName);
        paswword = pbeEnc.encrypt(paswword);

        userName_after = pbeEnc.encrypt(userName_before);
        System.out.println("###encrypt value :: >> " + userName_after);
        System.out.println("###decrypt  value :: >> " + pbeEnc.decrypt(userName_after));
        System.out.println("###decrypt URL value ::  " + pbeEnc.decrypt(url) + " && encrypt value :: " + url);
        System.out.println("###decrypt USER_NAME value ::  " + pbeEnc.decrypt(userName) + " && encrypt value :: " + userName);
        System.out.println("###decrypt PWD value ::  " + pbeEnc.decrypt(paswword) + " && encrypt value :: " + paswword);

    }

}