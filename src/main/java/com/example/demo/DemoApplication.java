package com.example.demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class DemoApplication {
	private static final Logger logger = LogManager.getLogger(DemoApplication.class.getName());

	public static void main(String[] args) {
		logger.debug("Debug message");
		logger.info("Info message");
		logger.warn("Warn message");
		logger.error("Error message");
		logger.fatal("Fatal message");

		SpringApplication.run(DemoApplication.class, args);
	}

}
