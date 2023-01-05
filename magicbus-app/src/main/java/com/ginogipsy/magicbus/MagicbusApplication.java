package com.ginogipsy.magicbus;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableEncryptableProperties
@EnableScheduling
@PropertySource(value = {
        "application.properties",
        "application-scheduler.properties",
        "application-persistence.properties",
        "application-security.properties",
        "application-mail-server.properties"})
public class MagicbusApplication {

    public static void main(String[] args) {
        SpringApplication.run(MagicbusApplication.class, args);
    }

}
