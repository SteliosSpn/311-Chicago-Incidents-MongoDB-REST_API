package com.chicago.nosql.rest.controllers;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.chicago.nosql.models.AllReports;
import com.chicago.nosql.models.Citizen;
import com.chicago.nosql.repositories.AllReportsRepository;
import com.chicago.nosql.repositories.CitizenRepository;
import com.github.javafaker.Faker;

@RestController
@RequestMapping("/allreports")
public class AllReportsController {

	@Autowired
	private AllReportsRepository repository;

	@Autowired
	private CitizenRepository cityrep;
	
	Citizen citizen;
	
	@RequestMapping(value = "/reports", method = RequestMethod.GET)
	public List<AllReports> getAllReports() {
	  return repository.findAll();
	}
	
	@RequestMapping(value = "/find3/{date}", method = RequestMethod.GET)
	public List<AllReports> find3(@PathVariable("date") String date) {
		return repository.findTop3ServiceRequestsPerZipCodeForDay(date);
	}
	
	@RequestMapping(value = "/find1/{mind}/{maxd}", method = RequestMethod.GET)
	public List<AllReports> find1(@PathVariable("mind") String mind, @PathVariable("maxd") String maxd) {
		return repository.findTotalRequestPerType(mind, maxd);
	}
	
	@RequestMapping(value = "/find2/{type_of_request}/{mind}/{maxd}", method = RequestMethod.GET)
	public List<AllReports> find2(@PathVariable("type_of_request") String type_of_request,@PathVariable("mind") String mind, @PathVariable("maxd") String maxd) {
		return repository.findTheNumberOfTotalRequestsPerDay2(type_of_request, mind, maxd);
	}
	
	@RequestMapping(value = "/find4/{type_of_request}", method = RequestMethod.GET)
	public List<AllReports> find4(@PathVariable("type_of_request") String type_of_request) {
		return repository.findLast3WardsforAServiceRequestType(type_of_request);
	}
	
	@RequestMapping(value = "/find5/{mind}/{maxd}", method = RequestMethod.GET)
	public List<AllReports> find5(@PathVariable("mind") String mind, @PathVariable("maxd") String maxd) {
		return repository.findAverageCompletionTimePerServiceRequest(mind, maxd);
	}
	
	@RequestMapping(value = "/find6/{minlat}/{maxlat}/{minlong}/{maxlong}/{day}", method = RequestMethod.GET)
	public List<AllReports> find6(@PathVariable("minlat") Double minlat, @PathVariable("maxlat") Double maxlat ,@PathVariable("minlong") Double minlong,@PathVariable("maxlong") Double maxlong,@PathVariable("day") String day) {
		return repository.findTypeByBoundingbox(day+"T00:00:00", minlat, maxlat, minlong, maxlong);
	}
	
	@RequestMapping(value = "/find7/{date}", method = RequestMethod.GET)
	public List<AllReports> find7(@PathVariable("date") String date) {
		return repository.find50MostUpvoted(date);
	}
	
	@RequestMapping(value = "/find8", method = RequestMethod.GET)
	public List<Citizen> find8() {
		return repository.find50MostActiveCitizens();
	}
	
	@RequestMapping(value = "/find9", method = RequestMethod.GET)
	public List<Citizen> find9() {
		return repository.find50Citizens_Wards();
	}
	
	@RequestMapping(value = "/find10", method = RequestMethod.GET)
	public List<Citizen> find10() {
		return repository.findDuplicates();
	}
	
	@RequestMapping(value = "/find11/{name}/{surname}", method = RequestMethod.GET)
	public List<AllReports> find11(@PathVariable("name") String name,@PathVariable("surname") String surname) {
		return repository.findWardbyName(name,surname);
	}
	
	@RequestMapping(value = "/incident", method = RequestMethod.POST)
	public String createIncident(@Valid @RequestBody AllReports report) {
		boolean flag=false;
		System.out.println(report.getType_of_service_request());
		//System.out.println(report.getNumber_of_black_carts_delivered());

		if(report.getType_of_service_request().equals("Abandoned Vehicle Complaint")){
			report.setNumber_of_black_carts_delivered(null);
			report.setDebris_location(null);
			report.setGraffiti_location(null);
			report.setNature_of_violation(null);
			report.setPotholes_filled(null);
			report.setPremises_baited(null);
			report.setPremises_with_garbage(null);
			report.setPremises_with_rats(null);
			report.setTree_location(null);
			report.setGraffiti_location(null);
			flag=true;
		//report.setCreation_date(Date.valueOf(LocalDate.now()).toString()+"T00:00:00");
		
		}
		
		else if(report.getType_of_service_request().equals("Alley Light Out")||report.getType_of_service_request().equals("Street Lights - All/Out")||report.getType_of_service_request().equals("Street Light Out")){
			report.setNumber_of_black_carts_delivered(null);
			//report.set
			report.setDebris_location(null);
			report.setGraffiti_location(null);
			report.setNature_of_violation(null);
			report.setPotholes_filled(null);
			report.setPremises_baited(null);
			report.setPremises_with_garbage(null);
			report.setPremises_with_rats(null);
			report.setTree_location(null);
			report.setCurrent_activity(null);
			report.setHow_many_days(null);
			report.setLicense_plate(null);
			report.setMost_recent_action(null);
			report.setSsa(null);
			report.setVehicle_model(null);
			report.setVehicle_color(null);
			report.setGraffiti_location(null);
			flag=true;
			
		}
		else if(report.getType_of_service_request().equals("Garbage Cart Black Maintenance/Replacement")){
			report.setDebris_location(null);
			report.setGraffiti_location(null);
			report.setNature_of_violation(null);
			report.setPotholes_filled(null);
			report.setPremises_baited(null);
			report.setPremises_with_garbage(null);
			report.setPremises_with_rats(null);
			report.setTree_location(null);
			report.setHow_many_days(null);
			report.setLicense_plate(null);
			report.setVehicle_model(null);
			report.setVehicle_color(null);
			report.setGraffiti_location(null);
			flag=true;	
			
		}
		else if (report.getType_of_service_request().equals("Graffiti Removal")){
			report.setNumber_of_black_carts_delivered(null);
			report.setDebris_location(null);
			report.setGraffiti_location(null);
			report.setNature_of_violation(null);
			report.setPotholes_filled(null);
			report.setPremises_baited(null);
			report.setPremises_with_garbage(null);
			report.setPremises_with_rats(null);
			report.setTree_location(null);
			report.setCurrent_activity(null);
			report.setHow_many_days(null);
			report.setLicense_plate(null);
			report.setMost_recent_action(null);
			report.setVehicle_model(null);
			report.setVehicle_color(null);
			flag=true;
		}
		else if(report.getType_of_service_request().equals("Pothole in Street")){
			report.setNumber_of_black_carts_delivered(null);
			report.setDebris_location(null);
			report.setGraffiti_location(null);
			report.setNature_of_violation(null);
			report.setPotholes_filled(null);
			report.setPremises_baited(null);
			report.setPremises_with_garbage(null);
			report.setPremises_with_rats(null);
			report.setTree_location(null);
			report.setHow_many_days(null);
			report.setLicense_plate(null);
			report.setVehicle_model(null);
			report.setVehicle_color(null);
			report.setGraffiti_location(null);
			flag=true;
		}
		else if(report.getType_of_service_request().equals("Rodent Baiting/Rat Complaint")){
			report.setNumber_of_black_carts_delivered(null);
			report.setDebris_location(null);
			report.setGraffiti_location(null);
			report.setNature_of_violation(null);
			report.setPotholes_filled(null);
			report.setTree_location(null);
			report.setHow_many_days(null);
			report.setLicense_plate(null);
			report.setSsa(null);
			report.setVehicle_model(null);
			report.setVehicle_color(null);
			report.setGraffiti_location(null);
			flag=true;
		}
		else if (report.getType_of_service_request().equals("Sanitation Code Violation")){
			report.setNumber_of_black_carts_delivered(null);
			report.setDebris_location(null);
			report.setGraffiti_location(null);
			report.setPotholes_filled(null);
			report.setPremises_baited(null);
			report.setPremises_with_garbage(null);
			report.setPremises_with_rats(null);
			report.setTree_location(null);
			report.setCurrent_activity(null);
			report.setHow_many_days(null);
			report.setLicense_plate(null);
			report.setMost_recent_action(null);
			report.setSsa(null);
			report.setVehicle_model(null);
			report.setVehicle_color(null);
			report.setGraffiti_location(null);
			flag=true;
		}
		else if(report.getType_of_service_request().equals("Tree Debris")){
			report.setNumber_of_black_carts_delivered(null);
			report.setGraffiti_location(null);
			report.setNature_of_violation(null);
			report.setPotholes_filled(null);
			report.setPremises_baited(null);
			report.setPremises_with_garbage(null);
			report.setPremises_with_rats(null);
			report.setTree_location(null);
			report.setHow_many_days(null);
			report.setLicense_plate(null);
			report.setSsa(null);
			report.setVehicle_model(null);
			report.setVehicle_color(null);
			report.setGraffiti_location(null);
			flag=true;
		}
		else if(report.getType_of_service_request().equals("Tree Trims")){
			report.setNumber_of_black_carts_delivered(null);
			report.setDebris_location(null);
			report.setGraffiti_location(null);
			report.setNature_of_violation(null);
			report.setPotholes_filled(null);
			report.setPremises_baited(null);
			report.setPremises_with_garbage(null);
			report.setPremises_with_rats(null);
			report.setCurrent_activity(null);
			report.setHow_many_days(null);
			report.setLicense_plate(null);
			report.setMost_recent_action(null);
			report.setSsa(null);
			report.setVehicle_model(null);
			report.setVehicle_color(null);
			report.setGraffiti_location(null);
			flag=true;
		}
		if (flag==false){return "Type of Service Request Invalid";}
		if(flag==true){
			report.setCreation_date(Date.valueOf(LocalDate.now()).toString()+"T00:00:00");
			report.setStatus("Open");
			report.setCompletion_date(null);
			report.setService_request_number(generateRandom(12));
			report.setAvgComplTime(null);
			report.setUpVoteCount(null);
			report.setCount(null);
			//return "Incident Report saved!";
		}
		//System.out.println(report.getCreation_date());
		repository.save(report);
		return "Incident Report saved!";
	}
	
	@RequestMapping(value = "/upvote/{citizen_id}/{incident_id}", method = RequestMethod.POST)
	public String upvote(@PathVariable("citizen_id") String citizen_id,@PathVariable("incident_id") String incident_id) {
		List<AllReports> incident = repository.findBy_id(incident_id);
		List<Citizen> citizen = cityrep.findBy_id(citizen_id);
		for(Citizen c:citizen){
			System.out.println(c.get_id());
			if(c.getUpvotes()!=null){
			List <String>upvotelist=c.getUpvotes();
			for(AllReports r:incident){
				for(String s:upvotelist){
					if(s.trim().contains(r.get_id())){
						return "Error!Citizen with id " +citizen_id+" has already upvoted the incident with id "+incident_id;
					}
				}
				upvotelist.add(incident_id);
				if(r.getUpVoteCount()==null)r.setUpVoteCount(1);
				else r.setUpVoteCount(r.getUpVoteCount()+1);
				repository.save(r);
			}
			c.setUpvotes(upvotelist);
			if(c.getUpvote_count()==null)c.setUpvote_count(1);
			else c.setUpvote_count(c.getUpvote_count()+1);
			cityrep.save(c);
		}
			else{
				List<String>upvotelist=new ArrayList<String>();
			upvotelist.add(incident_id);
				for(AllReports r:incident){
					if(r.getUpVoteCount()==null)r.setUpVoteCount(1);
					else r.setUpVoteCount(r.getUpVoteCount()+1);
					repository.save(r);
				}
				
			c.setUpvotes(upvotelist);
			c.setUpvote_count(1);
			cityrep.save(c);
			}
		}
		
		return "Success!Citizen with id " +citizen_id+" has upvoted the incident with id "+incident_id;
	}
	
	
	/*@RequestMapping(value = "/fake", method = RequestMethod.POST)
	public String createCitizen() {
		Faker faker = new Faker();
		List<String> testlist=new ArrayList<String>();
		List<AllReports> reports = new ArrayList<AllReports>();
		
		for (int i=0;i<100000;i++) {
			
			reports.addAll(repository.getfirst20(i*20));
			for(AllReports report:reports) {
				testlist.add(report.get_id());
				report.setUpVoteCount(1);
			    repository.save(report);
			}
			citizen = new Citizen();
			String firstName = faker.name().firstName();
			String lastName = faker.name().lastName();

			String address = faker.address().streetAddress();
			String phone =faker.phoneNumber().phoneNumber();
			citizen.setName(firstName);
			citizen.setSurname(lastName);
			citizen.setTelephone(phone);
			citizen.setAddress(address);
			citizen.setUpvotes(testlist);
			
			cityrep.save(citizen);
			
			//repository.findBy_id();
			
			
			reports.clear();
			testlist.clear();
		}
		return "citizens saved!";
	}*/
	
	
	
	
	
	
	
	
	
	
	public static String generateRandom(int length) {
	    Random random = new Random();
	    char[] digits = new char[length];
	    digits[0] = (char) (random.nextInt(9) + '1');
	    for (int i = 1; i < length; i++) {
	        digits[i] = (char) (random.nextInt(10) + '0');
	    }
	    return (new String(digits));
	}
	
}
