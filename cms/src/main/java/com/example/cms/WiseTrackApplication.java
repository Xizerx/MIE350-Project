package com.example.cms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WiseTrackApplication {

	private static Logger LOG = LoggerFactory
			.getLogger(WiseTrackApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(WiseTrackApplication.class, args);
		LOG.info("APPLICATION IS RUNNING");
	}
}
