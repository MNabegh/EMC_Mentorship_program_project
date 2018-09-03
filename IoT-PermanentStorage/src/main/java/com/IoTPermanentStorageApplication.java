package com;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

import elements.MongoInterfaceConfiguration;

@SpringBootApplication
public class IoTPermanentStorageApplication {
	private static final Logger logger =
			LoggerFactory.getLogger(MongoInterfaceConfiguration.class);

	public static void main(String[] args) {
		SpringApplication.run(IoTPermanentStorageApplication.class, args);
	}
	
	@KafkaListener(topics = "Transformer", groupId = "group-id")
	public void listen(String message) 
	{
		logger.info(message);
	}
}
