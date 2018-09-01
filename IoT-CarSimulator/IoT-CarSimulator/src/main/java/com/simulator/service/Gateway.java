package com.simulator.service;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

public class Gateway implements InitializingBean, ItemWriter<String>
{
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	public void sendMessage(String msg) {
		kafkaTemplate.send("Simulator", msg);
	}

	@Override
	public void write(List<? extends String> items) throws Exception {
		items.forEach(this::sendMessage);
		
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
