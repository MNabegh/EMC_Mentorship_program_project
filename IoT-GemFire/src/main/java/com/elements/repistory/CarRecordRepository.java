package com.elements.repistory;

import org.springframework.data.gemfire.repository.query.annotation.Trace;
import org.springframework.data.repository.CrudRepository;

import com.elements.repistory.POJO.CarRecord;

public interface CarRecordRepository  extends CrudRepository<CarRecord, String>
{
	@Trace
	public CarRecord findByVin(String vin);
}
