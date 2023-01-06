package com.ginogipsy.magicbus.scheduler;


import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

/**
 * @author ginogipsy
 */
@Component
@Slf4j
public class LogScheduler {

    @Value("${log.folder.path}")
    private String folderPath;

    @PostConstruct
    public void init() throws IOException {
        Files.createDirectories(Paths.get(folderPath));
    }

    @Scheduled(fixedRateString = "${write.log.period.minutes}", timeUnit = TimeUnit.MINUTES)
    public void printDateAndCorrectExecutionOfApp() throws IOException {
        log.info("LogScheduler - printDateAndCorrectExecutionOfApp() -> adding a line in log file..");
        final String fileName = DateTime.now().toString("YY-MM-dd").concat(".txt");
        final BufferedWriter writer = new BufferedWriter(new FileWriter(Paths.get(folderPath, fileName).toString(), true));
        writer.write(DateTime.now().toString(DateTimeFormat.fullDateTime()).concat(" -> fino a qui tutto bene ..\n"));
        writer.close();
    }
}
