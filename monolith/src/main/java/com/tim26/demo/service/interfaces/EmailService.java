package com.tim26.demo.service.interfaces;

import com.tim26.demo.model.User;
import org.springframework.mail.MailException;

public interface EmailService {

    public void sendDeclineNotificaitionAsync(User user) throws MailException, InterruptedException;
    public void sendAcceptNotificaitionAsync(User user) throws MailException, InterruptedException;
}
