package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.elements.mongo.CarRecordRepository;

@SpringBootApplication
public class IoTPermanentStorageApplication implements ApplicationRunner
{
	@Autowired
	private CarRecordRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(IoTPermanentStorageApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		repository.deleteAll();		
	}
	

}
