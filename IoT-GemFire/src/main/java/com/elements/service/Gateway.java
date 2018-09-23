package com.elements.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

public class Gateway
{
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	public void sendMessage(String msg)
	{
		kafkaTemplate.send("Gemfire", msg);
	}

}