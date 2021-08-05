package com.spring.nong4.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Override
    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("durk3501@gmail.com");
        message.setTo(to); //받는 사람 이메일 주소
        message.setSubject(subject); //제목
        message.setText(text); //내용
        emailSender.send(message);
    }

    @Override
    public void sendMimeMessage(String to, String subject, String text) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, false, "UTF-8");
            helper.setFrom("durk3501@gmail.com");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, true);
            emailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }


}
