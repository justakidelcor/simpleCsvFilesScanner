package com.loyaltygroup.test.filesProcessor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class FilesProcessorApplication {

	public static void main(String[] args) {
		SpringApplication.run(FilesProcessorApplication.class, args);
	}

}
