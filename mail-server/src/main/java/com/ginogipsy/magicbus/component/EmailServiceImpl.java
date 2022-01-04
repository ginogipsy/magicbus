package com.ginogipsy.magicbus.component;

import com.ginogipsy.magicbus.customexception.MessageException;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;


import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Component
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender emailSender;
    private final SimpleMailMessage templateMessage;

    public EmailServiceImpl(JavaMailSender emailSender, SimpleMailMessage templateMessage) {
        this.emailSender = emailSender;
        this.templateMessage = templateMessage;
    }

    public void sendSimpleMessage(
            String to, String subject, String text) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@magicbus.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);

    }


    @Override
    public void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment) {

        MimeMessage message = emailSender.createMimeMessage();
        try{
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom("noreply@magicbus.com");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text);
            FileSystemResource file
                    = new FileSystemResource(new File(pathToAttachment));
            helper.addAttachment("Invoice", file);
        }catch (MessagingException e){
            throw new MessageException("Message error!");
        }
        emailSender.send(message);

    }
}
