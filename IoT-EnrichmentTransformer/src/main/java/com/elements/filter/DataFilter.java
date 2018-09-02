package com.elements.filter;

import java.util.Iterator;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.elements.EnrichmentTransformerConfiguration;

public class DataFilter
{	
	private static final Logger logger =
			LoggerFactory.getLogger(EnrichmentTransformerConfiguration.class);

	public boolean filter(String message)
	{
		JSONObject jObject = new JSONObject(message);
		Iterator<?> keys = jObject.keys();
		while(keys.hasNext())
		{
			String key = (String)keys.next();
			if(jObject.get(key).toString().trim().length() == 0)
			{
				logger.info("drop record" + jObject.get("ord").toString());
				return false;
			}
		}
		return true;
	}
}
