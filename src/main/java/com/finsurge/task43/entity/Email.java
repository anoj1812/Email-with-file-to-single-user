package com.finsurge.task43.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Email {
    private String emailTo;
    private String emailSub;
    private String emailmsg;
    private MultipartFile emailFile;

    public MultipartFile getEmailFile() {
        return emailFile;
    }

    public void setEmailFile(MultipartFile emailFile) {
        this.emailFile = emailFile;
    }

    public String getEmailTo() {
        return emailTo;
    }

    public void setEmailTo(String emailTo) {
        this.emailTo = emailTo;
    }

    public String getEmailSub() {
        return emailSub;
    }

    public void setEmailSub(String emailSub) {
        this.emailSub = emailSub;
    }

    public String getEmailmsg() {
        return emailmsg;
    }

    public void setEmailmsg(String emailmsg) {
        this.emailmsg = emailmsg;
    }
}

