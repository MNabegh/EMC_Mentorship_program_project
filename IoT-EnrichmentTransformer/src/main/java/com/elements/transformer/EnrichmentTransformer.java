package com.elements.transformer;

import org.springframework.beans.factory.annotation.Autowired;
import com.elements.service.Gateway;

public class EnrichmentTransformer
{
	@Autowired
	public Gateway gateway;
	
	public void transform()//JSONObject filteredMessage)
	{
		//gateway.sendMessage(transformedMessage);
	}
}
