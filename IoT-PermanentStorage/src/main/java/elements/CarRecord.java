package elements;

import org.json.JSONObject;
import org.springframework.data.annotation.Id;

public class CarRecord
{
	@Id
	public String VIN;
	
	public int ord, ALTITUDE, BAROMETRIC_PRESSURE, ENGINE_COOLANT_TEMP,
	AMBIENT_AIR_TEMP, ENGINE_RPM, INTAKE_MANIFOLD_PRESSURE, AIR_INTAKE_TEMP,
	SPEED, EQUIV_RATIO;
	
	public double LATITUDE, LONGITUDE, FUEL_LEVEL, ENGINE_LOAD, MAF,
	SHORT_TERM_FUEL_TRIM_BANK_1, THROTTLE_POS, TIMING_ADVANCE;
	
	public String ENGINE_RUNTIME, DTC_NUMBER;
	
	public CarRecord(String message)
	{
		JSONObject jObject = new JSONObject(message);
		
		VIN = jObject.getString("VEHICLE_ID").toString();
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
		EQUIV_RATIO = Double
				.parseDouble(jObject.getString("EQUIV_RATIO").toString());
		
		LATITUDE = Double.parseDouble(jObject.getString("LATITUDE").toString());
		LONGITUDE = Double.parseDouble(jObject.getString("LONGITUDE").toString());
		FUEL_LEVEL = Double.parseDouble(jObject.getString("FUEL_LEVEL").toString());
		ENGINE_LOAD = Double.parseDouble(jObject.getString("ENGINE_LOAD").toString());
		MAF = Double.parseDouble(jObject.getString("MAF").toString());
		SHORT_TERM_FUEL_TRIM_BANK_1 = Double
				.parseDouble(jObject.getString("SHORT_TERM_FUEL_TRIM_BANK_1").toString());
		THROTTLE_POS = Double
				.parseDouble(jObject.getString("THROTTLE_POS").toString());
		TIMING_ADVANCE = Double
				.parseDouble(jObject.getString("TIMING_ADVANCE").toString());
		
		System.out.println(jObject.getString("timestamp").toString());
	}
	
	

}
