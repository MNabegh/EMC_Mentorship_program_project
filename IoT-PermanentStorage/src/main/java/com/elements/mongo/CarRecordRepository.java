package com.elements.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CarRecordRepository extends MongoRepository<CarRecord, String>
{
	public CarRecord findByVin (String vin);
	public CarRecord findByOrd (int ord);
}
