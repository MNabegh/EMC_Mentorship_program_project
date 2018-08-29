package java.com.simulator;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class RecordFieldSetMapper implements FieldSetMapper<Map<String,String>>
{
	@Override
	public Map<String, String> mapFieldSet(FieldSet fieldSet) throws BindException
	{
		Map<String, String> map = new HashMap<String, String>();
		map.put("ord", fieldSet.readString(0));
		map.put("LATITUDE", fieldSet.readString(1));
		map.put("LONGITUDE", fieldSet.readString(2));
		map.put("ALTITUDE", fieldSet.readString(3));
		map.put("VEHICLE_ID", fieldSet.readString(4));
		map.put("BAROMETRIC_PRESSURE", fieldSet.readString(5));
		map.put("ENGINE_COOLANT_TEMP", fieldSet.readString(6));
		map.put("FUEL_LEVEL", fieldSet.readString(7));
		map.put("ENGINE_LOAD", fieldSet.readString(8));
		map.put("AMBIENT_AIR_TEMP", fieldSet.readString(9));
		map.put("ENGINE_RPM", fieldSet.readString(10));
		map.put("INTAKE_MANIFOLD_PRESSURE", fieldSet.readString(11));
		map.put("MAF", fieldSet.readString(12));
		map.put("AIR_INTAKE_TEMP", fieldSet.readString(13));
		map.put("SPEED", fieldSet.readString(14));
		map.put("SHORT_TERM_FUEL_TRIM_BANK_1", fieldSet.readString(15));
		map.put("ENGINE_RUNTIME", fieldSet.readString(16));
		map.put("THROTTLE_POS", fieldSet.readString(17));
		map.put("DTC_NUMBER", fieldSet.readString(18));
		map.put("TIMING_ADVANCE", fieldSet.readString(19));
		map.put("EQUIV_RATIO", fieldSet.readString(20));
		
		return map;
		
	}

}
