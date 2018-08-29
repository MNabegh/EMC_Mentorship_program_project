package java.com.simulator;

import java.util.Arrays;

public enum Fields
{
	ord,
	LATITUDE,
	LONGITUDE,
	ALTITUDE,
	VEHICLE_ID,
	BAROMETRIC_PRESSURE,
	ENGINE_COOLANT_TEMP,
	FUEL_LEVEL,
	ENGINE_LOAD,
	AMBIENT_AIR_TEMP,
	ENGINE_RPM,
	INTAKE_MANIFOLD_PRESSURE,
	MAF,
	AIR_INTAKE_TEMP,
	SPEED,
	SHORT_TERM_FUEL_TRIM_BANK_1,
	ENGINE_RUNTIME,
	THROTTLE_POS,
	DTC_NUMBER,
	TIMING_ADVANCE,
	EQUIV_RATIO;
	
	public static String[] getNames()
	{
	    return Arrays.stream(Fields.class.getEnumConstants()).map(Enum::name).toArray(String[]::new);
	}
}
