package com.elements;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import com.elements.filter.DataFilter;
import com.elements.transformer.EnrichmentTransformer;

@Configuration
@PropertySource(value = { "classpath:application.properties" })
public class EnrichmentTransformerConfiguration
{
	private static final Logger logger =
			LoggerFactory.getLogger(EnrichmentTransformerConfiguration.class);

	@Autowired
	private Environment env;
	
	@Autowired
	public EnrichmentTransformer transformer;
	
	@Autowired
	public DataFilter dataFilter;

	@Bean
	public ConsumerFactory<String, String> consumerFactory() {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,  env.getRequiredProperty("broker.ip"));
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "group-id");
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		return new DefaultKafkaConsumerFactory<>(props);
	}
	
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, String> 
		factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		return factory;
	}
	
	@Bean
	public DataFilter dataFilter()
	{
		return new DataFilter();
	}
	
	@Bean
	public EnrichmentTransformer transformer()
	{
		return new EnrichmentTransformer();
	}
	
	@KafkaListener(topics = "Simulator", groupId = "group-id")
	public void listen(String message) 
	{
	   //System.out.println("Received Messasge in group - group-id: " + message);
	   dataFilter.filter(message);
	}

}
