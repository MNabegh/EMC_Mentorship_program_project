package com.elements;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.elements.repistory.CarRecordRepository;
import com.elements.repistory.POJO.CarRecord;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableKafka
@ClientCacheApplication(name = "IoTGemFireApplication", logLevel = "error")
@EnableEntityDefinedRegions(basePackageClasses = CarRecord.class,
clientRegionShortcut = ClientRegionShortcut.LOCAL)
@EnableGemfireRepositories
@RestController
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
		/*CarRecord test = repository.findByVin(newRecord.getVin());
		ObjectMapper mapper = new ObjectMapper();
		try {
			//String jsonInString = mapper.writeValueAsString(test);
			String address = "/home/nabegh/Learn/EMC_Mentorship_program_project/IoT-Dashboard/src/assets/"+test.getVin()+".txt";
			String jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(test);
		    File file = new File(address);
		    file.createNewFile();
			BufferedWriter out = new BufferedWriter(new FileWriter(address));
		    out.write(jsonInString);
		    out.close();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
	@RequestMapping("/carRecord")
	@ResponseBody
    public CarRecord carRecord(@RequestParam(value="vin", defaultValue="control1") String vin) {
        return repository.findByVin(vin);
    }

}
