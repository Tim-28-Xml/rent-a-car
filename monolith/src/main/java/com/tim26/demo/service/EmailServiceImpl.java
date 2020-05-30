package com.tim26.demo.service;

import com.tim26.demo.model.User;
import com.tim26.demo.service.interfaces.EmailService;
import com.tim26.demo.service.interfaces.UService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;


    @Autowired
    private Environment env;

    @Autowired
    private UService uService;

    @Override
    @Async
    public void sendDeclineNotificaitionAsync(User user) throws MailException, InterruptedException {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(user.getEmail());
        mail.setFrom(env.getProperty("spring.mail.username"));
        mail.setSubject("Rent a Car: Registration");
        mail.setText("Hello " + user.getUsername() + ",\n\nYour request for registration has been declined.\n\n" + "Rent a Car Team\n");
        javaMailSender.send(mail);

    }

    @Override
    @Async
    public void sendAcceptNotificaitionAsync(User user) throws MailException, InterruptedException {

        user.setVerificationCode(UUID.randomUUID().toString());
        uService.save(user);

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(user.getEmail());
        mail.setFrom(env.getProperty("spring.mail.username"));
        mail.setSubject("Rent a Car: Confirm account");
        mail.setText("Hello " + user.getUsername() + ",\n\nYour request for registration has been accepted. Click the following link to activate your account:\n\n" + "http://localhost:8082/users/confirm-account/" + user.getVerificationCode() + "\n\nRent a Car Team");
        javaMailSender.send(mail);
    }
}
