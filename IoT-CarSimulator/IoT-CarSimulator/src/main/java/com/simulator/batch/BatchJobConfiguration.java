package java.com.simulator.batch;

import java.com.simulator.Fields;
import java.com.simulator.RecordFieldSetMapper;
import java.util.Map;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;

import org.json.*;

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
    
    @Bean
    public FlatFileItemReader<Map<String, String>> reader()
    {
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
    	return item -> {
    		JSONObject json = new JSONObject(item);
    		String processedItem = json.toString();
    		long delay = Long.parseLong(env.getRequiredProperty("delay.record"));
    		Thread.sleep(delay);
    		return processedItem;
    	};
    }
    
    
	
}
