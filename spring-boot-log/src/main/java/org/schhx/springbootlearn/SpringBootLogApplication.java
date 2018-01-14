package org.schhx.springbootlearn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootLogApplication {

	private static Logger logger = LoggerFactory.getLogger(SpringBootLogApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringBootLogApplication.class, args);

		logger.trace("msg:{}", "trace");
		logger.debug("msg:{}", "debug");
		logger.info("msg:{}", "info");
		logger.warn("msg:{}", "warn");
		logger.error("msg:{}", "error");
	}
}
