package com.ginogipsy.magicbus.component;

import com.ginogipsy.magicbus.dto.UserDTO;


import javax.mail.MessagingException;
import java.io.IOException;

public interface EmailService {

    public void sendEmail(final UserDTO userDTO) throws MessagingException, IOException;
    public void sendRegistrationMail(final UserDTO userDTO) throws MessagingException, IOException;
}
