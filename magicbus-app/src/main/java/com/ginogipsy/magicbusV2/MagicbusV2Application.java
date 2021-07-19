package com.ginogipsy.magicbusV2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = {"application.properties", "application-persistence.properties", "application-security.properties"}, ignoreResourceNotFound = true)
public class MagicbusV2Application {

    public static void main(String[] args) {
        SpringApplication.run(MagicbusV2Application.class, args);
    }

}
