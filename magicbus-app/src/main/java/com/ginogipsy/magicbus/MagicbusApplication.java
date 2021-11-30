package com.ginogipsy.magicbus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = {"application.properties", "application-persistence.properties", "application-security.properties"})
public class MagicbusApplication {

    public static void main(String[] args) {
        SpringApplication.run(MagicbusApplication.class, args);
    }

}