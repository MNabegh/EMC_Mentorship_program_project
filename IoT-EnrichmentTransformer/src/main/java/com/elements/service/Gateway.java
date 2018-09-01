package com.elements.service;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

public class Gateway implements InitializingBean
{
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	public void sendMessage(String msg)
	{
		kafkaTemplate.send("Simulator", msg);
	}

	@Override
	public void afterPropertiesSet() throws Exception 
	{
		// TODO Auto-generated method stub	
	}

}