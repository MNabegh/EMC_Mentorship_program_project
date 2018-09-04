package com.elements.repistory.POJO;

import java.io.Serializable;import java.util.Calendar;
import org.json.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.gemfire.mapping.annotation.Region;

import lombok.Getter;

@Region(value = "CarRecords")
public class CarRecord implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1;

	@Id
    @Getter
	private String vin;
	
	private int ord, ALTITUDE, BAROMETRIC_PRESSURE, ENGINE_COOLANT_TEMP,
	AMBIENT_AIR_TEMP, ENGINE_RPM, INTAKE_MANIFOLD_PRESSURE, AIR_INTAKE_TEMP,
	SPEED;
	
	private double LATITUDE, LONGITUDE, FUEL_LEVEL, ENGINE_LOAD, MAF,
	SHORT_TERM_FUEL_TRIM_BANK_1, THROTTLE_POS;
	
	private String ENGINE_RUNTIME, DTC_NUMBER;
	
	private String timestamp;
	
	@PersistenceConstructor
	public CarRecord(String message)
	{
		JSONObject jObject = new JSONObject(message);
		
		vin = jObject.getString("VEHICLE_ID").toString();
		ENGINE_RUNTIME = jObject.getString("ENGINE_RUNTIME").toString();
		DTC_NUMBER = jObject.getString("DTC_NUMBER").toString();
		
		ord = Integer.parseInt(jObject.getString("ord").toString());
		ALTITUDE = Integer.parseInt(jObject.getString("ALTITUDE").toString());
		BAROMETRIC_PRESSURE = Integer
				.parseInt(jObject.getString("BAROMETRIC_PRESSURE").toString());
		ENGINE_COOLANT_TEMP = Integer.
				parseInt(jObject.getString("ENGINE_COOLANT_TEMP").toString());
		AMBIENT_AIR_TEMP = Integer
				.parseInt(jObject.getString("AMBIENT_AIR_TEMP").toString());
		ENGINE_RPM = Integer
				.parseInt(jObject.getString("ENGINE_RPM").toString());
		INTAKE_MANIFOLD_PRESSURE = Integer
				.parseInt(jObject.getString("INTAKE_MANIFOLD_PRESSURE").toString());
		AIR_INTAKE_TEMP = Integer
				.parseInt(jObject.getString("AIR_INTAKE_TEMP").toString());
		SPEED = Integer.parseInt(jObject.getString("SPEED").toString());
		
		LATITUDE = Double.parseDouble(jObject.getString("LATITUDE").toString());
		LONGITUDE = Double.parseDouble(jObject.getString("LONGITUDE").toString());
		FUEL_LEVEL = Double.parseDouble(jObject.getString("FUEL_LEVEL").toString());
		ENGINE_LOAD = Double.parseDouble(jObject.getString("ENGINE_LOAD").toString());
		MAF = Double.parseDouble(jObject.getString("MAF").toString());
		SHORT_TERM_FUEL_TRIM_BANK_1 = Double
				.parseDouble(jObject.getString("SHORT_TERM_FUEL_TRIM_BANK_1").toString());
		THROTTLE_POS = Double
				.parseDouble(jObject.getString("THROTTLE_POS").toString());
		
		long tempTimestamp = Long.parseLong(jObject.getString("timestamp").toString());
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(tempTimestamp);
		String date = c.get(Calendar.YEAR)+"-"+c.get(Calendar.MONTH)+"-"+c.get(Calendar.DAY_OF_MONTH);
		String time = c.get(Calendar.HOUR_OF_DAY)+":"+c.get(Calendar.MINUTE)+":"+c.get(Calendar.SECOND);
		timestamp = date + " " + time;

	}

	public String getVin() {
		return vin;
	}

	public int getOrd() {
		return ord;
	}

	public int getALTITUDE() {
		return ALTITUDE;
	}

	public int getBAROMETRIC_PRESSURE() {
		return BAROMETRIC_PRESSURE;
	}

	public int getENGINE_COOLANT_TEMP() {
		return ENGINE_COOLANT_TEMP;
	}

	public int getAMBIENT_AIR_TEMP() {
		return AMBIENT_AIR_TEMP;
	}

	public int getENGINE_RPM() {
		return ENGINE_RPM;
	}

	public int getINTAKE_MANIFOLD_PRESSURE() {
		return INTAKE_MANIFOLD_PRESSURE;
	}

	public int getAIR_INTAKE_TEMP() {
		return AIR_INTAKE_TEMP;
	}

	public int getSPEED() {
		return SPEED;
	}

	public double getLATITUDE() {
		return LATITUDE;
	}

	public double getLONGITUDE() {
		return LONGITUDE;
	}

	public double getFUEL_LEVEL() {
		return FUEL_LEVEL;
	}

	public double getENGINE_LOAD() {
		return ENGINE_LOAD;
	}

	public double getMAF() {
		return MAF;
	}

	public double getSHORT_TERM_FUEL_TRIM_BANK_1() {
		return SHORT_TERM_FUEL_TRIM_BANK_1;
	}

	public double getTHROTTLE_POS() {
		return THROTTLE_POS;
	}

	public String getENGINE_RUNTIME() {
		return ENGINE_RUNTIME;
	}

	public String getDTC_NUMBER() {
		return DTC_NUMBER;
	}

	public String getTimestamp() {
		return timestamp;
	}

}
