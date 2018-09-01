package com.elements.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

import com.elements.transformer.EnrichmentTransformer;

public class DataFilter
{
	@Autowired
	public EnrichmentTransformer transformer;
	
	@KafkaListener(topics = "test", groupId = "group-id")
	public void listen(String message) 
	{
	   System.out.println("Received Messasge in group - group-id: " + message);
	   filter(message);
	}
	
	private void filter(String message)
	{
		//transformer.transform(filteredMessage);	
	}
}
