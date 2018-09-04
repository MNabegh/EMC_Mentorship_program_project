package com.elements;

import java.util.HashMap;
import java.util.Map;

import org.apache.geode.cache.client.ClientRegionShortcut;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.gemfire.config.annotation.ClientCacheApplication;
import org.springframework.data.gemfire.config.annotation.EnableEntityDefinedRegions;
import org.springframework.data.gemfire.repository.config.EnableGemfireRepositories;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;


import com.elements.repistory.CarRecordRepository;
import com.elements.repistory.POJO.CarRecord;

@Configuration
@EnableKafka
@ClientCacheApplication(name = "IoTGemFireApplication", logLevel = "error")
@EnableEntityDefinedRegions(basePackageClasses = CarRecord.class,
clientRegionShortcut = ClientRegionShortcut.LOCAL)
@EnableGemfireRepositories
public class GemFireConfiguration 
{
	@Autowired
	private Environment env;
	
	@Autowired
	private CarRecordRepository repository;
	
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
	
	@KafkaListener(topics = "Transformer", groupId = "group-id")
	public void listen(String message) 
	{
		CarRecord newRecord = new CarRecord(message);
		repository.save(newRecord);
		CarRecord test = repository.findByVin("control1");
		System.out.println("Got car " + test.getVin() + " Record " + test.getOrd());
	}

}
