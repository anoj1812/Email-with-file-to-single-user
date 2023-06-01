package com.finsurge.task43.service;

import jakarta.mail.AuthenticationFailedException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.AddressException;

import com.finsurge.task43.entity.Email;

import java.io.IOException;

public interface EmailService  {

    public void saveEmail(Email email);
    public void sendMail() throws AddressException, MessagingException, IOException, AuthenticationFailedException;
}

