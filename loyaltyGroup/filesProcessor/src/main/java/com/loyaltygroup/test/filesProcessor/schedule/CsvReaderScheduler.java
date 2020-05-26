package com.loyaltygroup.test.filesProcessor.schedule;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.loyaltygroup.test.filesProcessor.services.db.impl.CsvReaderImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CsvReaderScheduler {

    @Autowired
    CsvReaderImpl csvReaderImpl;

    private static final Logger log = LoggerFactory.getLogger(CsvReaderScheduler.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(cron = "*/30 * * * * *")
    public void reportCurrentTime() {

        try {

            csvReaderImpl.readAll();
            log.info("All CSV files has already been read. The time is now {}", dateFormat.format(new Date()));

        }catch (Exception e) {

            log.info(e.getMessage());

        }


    }
}

