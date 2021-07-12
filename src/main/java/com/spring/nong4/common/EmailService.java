package com.spring.nong4.common;

public interface EmailService {
    void sendSimpleMessage(String to, String subject, String text);
    void sendMimeMessage(String to, String subject, String text);
    //dsdsds
}
