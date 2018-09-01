package com.simulator.batch;

import java.util.HashMap;import java.util.Map;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import com.simulator.service.Gateway;
import com.sun.tools.sjavac.Log;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.json.JSONObject;
import org.slf4j.Logger;;

@Configuration
@EnableBatchProcessing
@PropertySource(value = { "classpath:application.properties" })
public class BatchJobConfiguration
{
	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	private Environment env;

	@Autowired
	public static Logger logger;

	@Bean
	public static DataSource dataSource() {
		EmbeddedDatabaseBuilder embeddedDatabaseBuilder = new EmbeddedDatabaseBuilder();
		return embeddedDatabaseBuilder
				.addScript("classpath:org/springframework/batch/core/schema-drop-hsqldb.sql")
				.addScript("classpath:org/springframework/batch/core/schema-hsqldb.sql")
				.setType(EmbeddedDatabaseType.HSQL)
				.build();
	}

	@Bean
	public FlatFileItemReader<Map<String, String>> reader()
	{
		System.out.println("enter reader method");
		return new FlatFileItemReaderBuilder<Map<String, String>>()
				.name("recordItemReader")
				.resource(new ClassPathResource("car.csv"))
				.delimited()
				.names(Fields.getNames())
				.fieldSetMapper(new RecordFieldSetMapper())
				.build();
	}

	@Bean
	public ItemProcessor<Map<String, String>, String> processor()
	{
		System.out.println("Enter processor method");
		return item -> {
			JSONObject json = new JSONObject(item);
			String processedItem = json.toString();
			long delay = Long.parseLong(env.getRequiredProperty("delay.record"));
			Thread.sleep(delay);
			return processedItem;
		};
	}

	@Bean
	public ProducerFactory<String, String> producerFactory() {
		System.out.println("enter kafka config method");
		Map<String, Object> configProps = new HashMap<>();
		configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, env.getRequiredProperty("broker.ip"));
		configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		return new DefaultKafkaProducerFactory<>(configProps);
	}
	@Bean
	public KafkaTemplate<String, String> kafkaTemplate() {
		System.out.println("enter kafka template method");
		return new KafkaTemplate<>(producerFactory());
	}

	@Bean
	public Gateway writer ()
	{
		System.out.println("enter writer method");
		return new Gateway();
	}

	@Bean
	public Step step1(Gateway writer) {
		return stepBuilderFactory.get("step1")
				.<Map<String, String>, String> chunk(1)
				.reader(reader())
				.processor(processor())
				.writer(writer)
				.build();
	}

	@Bean
	public Job simulatorJob(Step step1) {
		return jobBuilderFactory.get("simulatorJob")
				.incrementer(new RunIdIncrementer())
				.flow(step1)
				.end()
				.build();
	}
	// end::jobstep[]



}
