package com.finsurge.task43.service;

import com.finsurge.task43.repository.EmailRepository;
import jakarta.mail.*;
import jakarta.mail.internet.*;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.finsurge.task43.entity.Email;
import org.springframework.web.multipart.MultipartFile;

@Service
public class EmailServiceClass implements EmailService {
    @Autowired
    private EmailRepository emailRepository;

    public void saveEmail(Email email) {
        emailRepository.save(email);
    }
    @Override
    public void sendMail() throws AddressException, MessagingException, IOException,AuthenticationFailedException {
        Email email=emailRepository.getEmail();
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("< ur mail id >", "< ur password >");
            }
        });
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("< ur mail id >", false));

        String str=email.getEmailTo().trim();
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(str));
        msg.setSubject(email.getEmailSub());
        String content=email.getEmailmsg();
        msg.setContent(content,"text/plain");
        msg.setSentDate(new Date());
        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(content, "text/plain");
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        MimeBodyPart attachPart = new MimeBodyPart();
        MultipartFile file=email.getEmailFile();
        System.out.println(file.getOriginalFilename()) ;
        String filename=file.getOriginalFilename();
        File convFile = new File(System.getProperty("java.io.tmpdir")+"/"+filename);
        file.transferTo(convFile);
        attachPart.attachFile(convFile);
        multipart.addBodyPart(attachPart);
        msg.setContent(multipart);
        Transport.send(msg);
    }
}

