package com.tim26.AuthenticationService.service.interfaces;

import com.tim26.AuthenticationService.model.User;
import org.springframework.mail.MailException;

public interface EmailService {

    public void sendDeclineNotificaitionAsync(User user) throws MailException, InterruptedException;
    public void sendAcceptNotificaitionAsync(User user) throws MailException, InterruptedException;
}
