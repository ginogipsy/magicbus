package com.ginogipsy.magicbus.component;

import com.ginogipsy.magicbus.dto.UserDTO;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;


@Service
public class EmailServiceImpl implements EmailService {

    private static final String MAGICBUS_LOGO_IMAGE = "templates/images/magic-bus-logo.png";
    private static final String PNG_MIME = "image/png";

    private final JavaMailSender emailSender;
    private final SpringTemplateEngine templateEngine;
    private final Environment environment;

    public EmailServiceImpl(JavaMailSender emailSender, SpringTemplateEngine templateEngine, Environment environment) {
        this.emailSender = emailSender;
        this.templateEngine = templateEngine;
        this.environment = environment;
    }

    @Override
    public void sendEmail(UserDTO userDTO) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

        //helper.addAttachment("magic-bus-logo", new ClassPathResource("images/magic-bus-logo.png"));
        //helper.addAttachment("alaska", new ClassPathResource("images/alaska.jpg"));

        Context context = new Context();
        context.setVariable("name",userDTO.getUsername());
        context.setVariable("location", "Colleberardi");
        context.setVariable("sign", "v-mezz");
        context.setVariable("magicbusLogo", "./images/magic-bus-logo.png");
        context.setVariable("alaska", "./images/alaska.jsp");
        String html = templateEngine.process("welcome-template", context);

        helper.setTo(userDTO.getEmail());
        helper.setText(html, true);
        helper.setSubject("Benvenuto a bordo sul magicbus!");
        helper.setFrom("magicbus-welcome@gmail.com");

        emailSender.send(message);
    }

    @Override
    public void sendRegistrationMail(UserDTO userDTO) throws MessagingException, IOException {

        final String mailFrom = environment.getProperty("spring.mail.properties.mail.smtp.from");
        final String mailFromName = environment.getProperty("mail.from.name", "Identity");

        final MimeMessage message = emailSender.createMimeMessage();
        final MimeMessageHelper email = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

        email.setTo(userDTO.getEmail());
        email.setSubject("Welcome to magicbus");
        email.setFrom(new InternetAddress(mailFrom, mailFromName));
        final Context ctx = new Context(LocaleContextHolder.getLocale());
        ctx.setVariable("email", userDTO.getEmail());
        ctx.setVariable("name", userDTO.getUsername());
        ctx.setVariable("magicbusLogo", MAGICBUS_LOGO_IMAGE);
        ctx.setVariable("url", "https://www.commissariatodips.it/");

        final String htmlContent = this.templateEngine.process("registration", ctx);
        email.setText(htmlContent, true);
        ClassPathResource clr = new ClassPathResource(MAGICBUS_LOGO_IMAGE);
        email.addInline("magicbusLogo", clr, PNG_MIME);
        emailSender.send(message);
    }
}
