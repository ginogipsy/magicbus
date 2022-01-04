package com.ginogipsy.magicbus.component;

import javax.mail.MessagingException;

public interface EmailService {

    void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment);
}
