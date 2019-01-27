package com.chicago.nosql.repositories;

import java.util.List;

import com.chicago.nosql.models.AllReports;
import com.chicago.nosql.models.Citizen;

public interface AllReportsRepositoryCustom {

	public List<AllReports> findTotalRequestPerType(String mind, String maxd);
	public Long findTheNumberOfTotalRequestsPerDay(String type_of_request,String mind, String maxd);
	public List<AllReports> findTheNumberOfTotalRequestsPerDay2(String type_of_request,String mind, String maxd);
	public List<AllReports> findTop3ServiceRequestsPerZipCodeForDay(String day);
	public List<AllReports> findLast3WardsforAServiceRequestType(String type_of_request);
	public List<AllReports> findAverageCompletionTimePerServiceRequest(String mind, String maxd);
	public List<AllReports> find50MostUpvoted(String date);
	public List<Citizen> find50MostActiveCitizens();
	public List<Citizen> find50Citizens_Wards();
	public List<AllReports> findTypeByBoundingbox(String day,Double minlat , Double maxlat ,Double minlong , Double maxlong);
	public List<AllReports> findWardbyName(String name , String surname);
	public List<Citizen> findDuplicates();
	public List<AllReports> getfirst20(int skip);
}
