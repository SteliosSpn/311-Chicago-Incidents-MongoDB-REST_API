package com.chicago.nosql.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.chicago.nosql.models.AllReports;
import com.chicago.nosql.models.Citizen;
public interface CitizenRepository extends MongoRepository<Citizen,String>{
	List<Citizen> findBy_id(String _id);
	List<Citizen> findBytelephone(String telephone);
}
