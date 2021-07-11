package com.spring.nong4.common;

import org.springframework.stereotype.Component;

@Component
public class MySecurityUtils {

    public int getRandomNumber(int eNumber) {
        return getRandomNumber(0, eNumber);
    }

    public int getRandomNumber(int sNumber, int eNumber) {
        return (int)(Math.random() * (eNumber - sNumber + 1)) + sNumber;
    }

    // len길이만큼의 랜덤한 숫자(0~9)를 문자열로 리턴
    public String getRandomDigit(int len) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<len; i++) {
            sb.append(getRandomNumber(9));
        }
        return sb.toString();
    }

    //숫자+영문자 랜덤하게 만드는 문자열 리턴 10 > "1ab9Dl7"
    /*
    public String getRandomString(int len) {

    }
     */
}
