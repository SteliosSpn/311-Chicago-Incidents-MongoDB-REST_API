package com.chicago.nosql.repositories;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.chicago.nosql.models.AllReports;

public interface AllReportsRepository extends MongoRepository<AllReports,String>, AllReportsRepositoryCustom{
	
	List<AllReports> findBy_id(String _id);
	
	@Query("{'Creation Date': {$gte: ?0, $lte:?1 }}")
	List<AllReports> findEx1(String mind, String maxd);
	//db.All_Reports.aggregate([{"$match":{'Creation Date':{"$gte":'2011-01-01',"$lte":'2011-03-03'}}},{"$group":{_id:"$Type of Service Request",count:{$sum:1}}},{$sort:{count:-1}}])
}
