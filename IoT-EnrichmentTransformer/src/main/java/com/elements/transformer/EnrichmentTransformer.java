package com.elements.transformer;

import org.json.JSONObject;

public class EnrichmentTransformer
{	
	public String transform(String filteredMessage)//JSONObject filteredMessage)
	{
		JSONObject jObject = new JSONObject(filteredMessage);
		jObject.accumulate("timestamp", System.currentTimeMillis()+"");
		
		return jObject.toString();
	}

}
