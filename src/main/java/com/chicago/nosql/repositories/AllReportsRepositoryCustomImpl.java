package com.chicago.nosql.repositories;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationOptions;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.chicago.nosql.models.AllReports;
import com.chicago.nosql.models.Citizen;

public class AllReportsRepositoryCustomImpl implements AllReportsRepositoryCustom{

	@Autowired
    MongoTemplate mongoTemplate;
	@Autowired
	CitizenRepository cityrep;
	
	

	@Override
	public List<AllReports> findTotalRequestPerType(String mind, String maxd) {
		Aggregation aggregation = Aggregation.newAggregation(
	            Aggregation.match(Criteria.where("Creation Date").gte(mind)
	    				.lte(maxd)),
	            Aggregation.group("Type of Service Request").count().as("count"),
	            Aggregation.sort(Sort.Direction.DESC,"count")
	            );
		
				AggregationResults<AllReports> result = mongoTemplate.aggregate(
				  aggregation, "All_Reports", AllReports.class);
				List<AllReports> document= result.getMappedResults();
		return document;
	}

	@Override
	public Long findTheNumberOfTotalRequestsPerDay(String type_of_request,String mind, String maxd) {
		Query query = new Query();
		query.addCriteria(Criteria.where("Creation Date").gte(mind).lte(maxd));
		query.addCriteria(Criteria.where("Type of Service Request").is(type_of_request));
		long count = mongoTemplate.count(query, "All_Reports");
		return count;
	}

	
	public List<AllReports> findTheNumberOfTotalRequestsPerDay2(String type_of_request, String mind, String maxd) {
		Aggregation aggregation = Aggregation.newAggregation(
	            Aggregation.match(Criteria.where("Creation Date").gte(mind)
	    				.lte(maxd)),
	            Aggregation.match(Criteria.where("Type of Service Request").is(type_of_request)),
	            Aggregation.group("Creation Date").count().as("count")
				 );
		
		AggregationResults<AllReports> result = mongoTemplate.aggregate(
		  aggregation, "All_Reports", AllReports.class);
		List<AllReports> document= result.getMappedResults();
		return document;
	}

	@Override
	public List<AllReports> findTop3ServiceRequestsPerZipCodeForDay(String date) {
		Aggregation aggregation = Aggregation.newAggregation(
	            Aggregation.match(Criteria.where("Creation Date").is(date+"T00:00:00")),
	            Aggregation.group("ZIP Code","Type of Service Request").count().as("count"),
	            Aggregation.sort(Sort.Direction.DESC,"ZIP Code","count")
	            //Aggregation.limit(3)
	            );
		
				AggregationResults<AllReports> result = mongoTemplate.aggregate(
				  aggregation, "All_Reports", AllReports.class);
				List<AllReports> document= result.getMappedResults();
				List<AllReports> finaldoc=new ArrayList<AllReports>();
				Map<Integer,Integer> newMap=new LinkedHashMap<Integer,Integer>();
				
				for(AllReports doc:document){
					if(newMap.containsKey(doc.getZip_code())){
						
						Integer x=newMap.get(doc.getZip_code());
						if(x<3){
							newMap.put(doc.getZip_code(),x+1);
							finaldoc.add(doc);
						}
					}
					else {
						newMap.put(doc.getZip_code(),1);
					finaldoc.add(doc);}
				}
				
				
		return finaldoc;
	}

	@Override
	public List<AllReports> findLast3WardsforAServiceRequestType(String type_of_request) {
		Aggregation aggregation = Aggregation.newAggregation(
				Aggregation.match(Criteria.where("Type of Service Request").is(type_of_request)),
				Aggregation.match(Criteria.where("Ward").gt(0)),
	            Aggregation.group("Type of Service Request","Ward").count().as("count"),
	            Aggregation.sort(Sort.Direction.ASC,"count"),
	            Aggregation.limit(3)
	            );
		
				AggregationResults<AllReports> result = mongoTemplate.aggregate(
				  aggregation, "All_Reports", AllReports.class);
				List<AllReports> document= result.getMappedResults();
		return document;
	}

	@Override
	public List<AllReports> findAverageCompletionTimePerServiceRequest(String mind, String maxd) {
		Date creation_date = null;
		Date completion_date = null;
		List<AllReports> list = getBetweenDates(mind,maxd);
		List<AllReports> document = groupByTypeOfServiceRequest(mind,maxd);
		for(AllReports report: list) {
			if(!report.getCreation_date().equals(null) && !report.getCompletion_date().equals(null)) {
				creation_date = new Date(Timestamp.
						valueOf(report.getCreation_date().replaceAll("T", " ")).getTime());
				completion_date = new Date(Timestamp.
						valueOf(report.getCompletion_date().replaceAll("T", " ")).getTime());
			}
			
					for(AllReports doc: document) {
						if(doc.get_id().equals(report.getType_of_service_request())) {
							if(doc.getAvgComplTime() == null) {
								doc.setAvgComplTime(completion_date.getTime() - creation_date.getTime());
							}
							else {
								doc.setAvgComplTime(doc.
										getAvgComplTime() + (completion_date.getTime() - creation_date.getTime()));
							}
						}
					}
		}
		for(AllReports doc: document) {
			doc.setAvgComplTime((doc.
					getAvgComplTime()/Long.parseLong(doc.getCount()) / (24 * 60 * 60 * 1000)));
		}
		return document;
	}
	
	public List<AllReports> getBetweenDates(String mind, String maxd){
		Query query = new Query();
		query.addCriteria(Criteria.where("Creation Date").gte(mind).lte(maxd));
		return mongoTemplate.find(query, AllReports.class);
	}
	
	public List<AllReports> groupByTypeOfServiceRequest(String mind, String maxd){
		Aggregation aggregation = Aggregation.newAggregation(
				 Aggregation.match(Criteria.where("Creation Date").gte(mind)
		    				.lte(maxd)),
	            Aggregation.group("Type of Service Request").count().as("count")
	            );
		
				AggregationResults<AllReports> result = mongoTemplate.aggregate(
				  aggregation, "All_Reports", AllReports.class);
				List<AllReports> document= result.getMappedResults();
				return document;
	}

	@Override
	public List<AllReports> getfirst20(int skip) {
		Query query = new Query();
		query.skip(skip).limit(20);
		return mongoTemplate.find(query, AllReports.class);
	}

	@Override
	public List<AllReports> find50MostUpvoted(String date) {
		Aggregation aggregation = Aggregation.newAggregation(
				Aggregation.match(Criteria.where("Creation Date").is(date+"T00:00:00")),
	            Aggregation.sort(Sort.Direction.DESC,"upvote_count"),
	            Aggregation.limit(50)
	            );
		
				AggregationResults<AllReports> result = mongoTemplate.aggregate(
				  aggregation, "All_Reports", AllReports.class);
				List<AllReports> document= result.getMappedResults();
		return document;
	}

	@Override
	public List<Citizen> find50MostActiveCitizens() {
		Aggregation aggregation = Aggregation.newAggregation(
	            Aggregation.sort(Sort.Direction.DESC,"upvote_count"),
	            Aggregation.limit(50)
	            );
		
				AggregationResults<Citizen> result = mongoTemplate.aggregate(
				  aggregation, "Citizens", Citizen.class);
				List<Citizen> document= result.getMappedResults();
		return document;
	}

	@Override
	public List<Citizen> find50Citizens_Wards() {
		List<Citizen> citizens = getSomeCitizens(100000);
		HashMap<Citizen,ArrayList<Integer>> max = new HashMap<Citizen,ArrayList<Integer>>();
		
		for(Citizen citizen :citizens) {
			for(String id: citizen.getUpvotes()) {
				Aggregation aggregation = Aggregation.newAggregation(
						Aggregation.match(Criteria.where("_id").is(id))
						 );
				AggregationResults<AllReports> result = mongoTemplate.aggregate(
						  aggregation, "All_Reports", AllReports.class);
				List<AllReports> document = result.getMappedResults();
				//System.out.println(document.get(0).getWard());
				if(max.containsKey(citizen)) {
					if(!max.get(citizen).contains(document.get(0).getWard())) {
						max.get(citizen).add(document.get(0).getWard());
					}
				}
				else {
					max.put(citizen, new ArrayList<Integer>());
					max.get(citizen).add(document.get(0).getWard());
				}
			}
		}
		
		List<Citizen> keys = new ArrayList<>();
		for(int i=0; i<50; i++) {
			int maxLen = 0;
			for(Map.Entry<Citizen, ArrayList<Integer>> entry : max.entrySet()) {
				if(entry.getValue().size() > maxLen) {
					maxLen = entry.getValue().size();
				}
			}
			System.out.println(i+" maximum: "+maxLen);
			for(Map.Entry<Citizen, ArrayList<Integer>> entry : max.entrySet()) {
				if(entry.getValue().size() == maxLen) {
					keys.add(entry.getKey());
					max.put(entry.getKey(), new ArrayList<Integer>());
			    	break;
				}
			}
			
		}
		return keys;
	}
	
	public List<Citizen> getSomeCitizens(int limit){
		Query query = new Query();
		query.limit(limit);
		return mongoTemplate.find(query, Citizen.class);
	}

	@Override
	public List<AllReports> findTypeByBoundingbox(String day, Double minlat,
			Double maxlat, Double minlong, Double maxlong) {
		
	
		
		Aggregation aggregation = Aggregation.newAggregation(
		Aggregation.match(Criteria.where("Latitude").gte(minlat).lte(maxlat)),
		Aggregation.match(Criteria.where("Longitude").gte(minlong).lte(maxlong)),
		Aggregation.match(Criteria.where("Creation Date").is(day)),
				Aggregation.group("Type of Service Request").count().as("count"),
				Aggregation.sort(Sort.Direction.DESC,"count"),
				Aggregation.limit(1)
			
       
        
       );
		
		AggregationResults<AllReports> result = mongoTemplate.aggregate(
				  aggregation, "All_Reports", AllReports.class);
				List <AllReports> document = result.getMappedResults();
		
		return document;
	}

	@Override
	public List<AllReports> findWardbyName(String name, String surname) {
		Aggregation aggregation = Aggregation.newAggregation(
				Aggregation.match(Criteria.where("name").is(name)),
						Aggregation.match(Criteria.where("surname").is(surname))
				
				);
		
		AggregationResults<Citizen> citizenresult = mongoTemplate.aggregate(
				  aggregation, "Citizens", Citizen.class);
				List <Citizen> document = citizenresult.getMappedResults();
				List<String> incidentsid = new ArrayList<String>();
				for(Citizen c:document){
					//System.out.println(c.getName()+c.getSurname()+c.getUpvotes());
					
					incidentsid.addAll(c.getUpvotes());
				
					}
			
				
				List<AllReports> allreportslist = new ArrayList<AllReports>();
				for(String s:incidentsid){
					System.out.println(s);
					Aggregation aggregation1=Aggregation.newAggregation(
							Aggregation.match(Criteria.where("_id").is(s)),
							//Aggregation.project("Ward")
							Aggregation.group("Ward")
							
							);
					
					AggregationResults<AllReports> allreportsresult = mongoTemplate.aggregate(
							  aggregation1, "All_Reports", AllReports.class);
					
					List <AllReports> document1 = allreportsresult.getMappedResults();
					
					allreportslist.addAll(document1);
				}
				
				for(AllReports ar:allreportslist){System.out.println(ar.getWard());}
		
		return allreportslist;
	}

	@Override
	public List<Citizen> findDuplicates() {
		
		Aggregation aggregation = Aggregation.newAggregation(
				Aggregation.group("telephone").count().as("count"),
	            Aggregation.sort(Sort.Direction.DESC,"count"),
	            Aggregation.match(Criteria.where("count").gt(1))
	            );
		AggregationResults<Citizen> result = mongoTemplate.aggregate(
				  aggregation, "Citizens", Citizen.class);
				List<Citizen> document= result.getMappedResults();
				
				List<Citizen>document1 = new ArrayList<Citizen>();
				List<Citizen>document2 = new ArrayList<Citizen>();
				for(Citizen c:document){
					//System.out.println(c.get_id());
					List<Citizen> citizen = cityrep.findBytelephone(c.get_id());
					for(Citizen innerc:citizen){
						Aggregation aggregation1 = Aggregation.newAggregation(
								Aggregation.unwind("upvotes"),
								Aggregation.match(Criteria.where("_id").is(innerc.get_id())),
								Aggregation.group("upvotes"),
								Aggregation.group("_id").count().as("count"),
								Aggregation.match(Criteria.where("count").gt(0))
			
								
					            );
						AggregationResults<Citizen> result1 = mongoTemplate.aggregate(
								  aggregation1, "Citizens", Citizen.class);
								List<Citizen>temp= result1.getMappedResults();
								
								
								
								
								
								document1.addAll(temp);
						
					
				}
					Map<String,Integer> newMap=new LinkedHashMap<String,Integer>();
					for(Citizen doc:document1){
						
						if(newMap.containsKey(doc.get_id())){
							
							Integer x=newMap.get(doc.get_id());
							newMap.put(doc.get_id(),x+1);
						}
						else newMap.put(doc.get_id(),1);
					}
					
					
					newMap.forEach((k,v) -> {
					    if(newMap.get(k)>1){
					    	Citizen newCitizen = new Citizen();
					    	newCitizen.set_id(k);
					    	document2.add(newCitizen);}
					});
		
		
	}
				return document2;}

	

	
	
	
	
}
	
