package com.chicago.nosql.models;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "All_Reports")
public class AllReports{

	@Id
	private String _id;
	
	private Integer upvote_count;
	
	public Integer getUpVoteCount() {
		return upvote_count;
	}
	public void setUpVoteCount(Integer upVoteCount) {
		this.upvote_count = upVoteCount;
	}

	private String count;
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	
	private Long avgComplTime;
	
	public Long getAvgComplTime() {
		return avgComplTime;
	}
	public void setAvgComplTime(Long avgComplTime) {
		this.avgComplTime = avgComplTime;
	}

	@Field("Creation Date")
	private String creation_date;
	@Field("Status")
	private String status;
	@Field("Completion Date")
	private String completion_date;
	@Field("Service Request Number")
	private String service_request_number;
	@Field("Type of Service Request")
	private String type_of_service_request;
	@Field("Street Address")
	private String street_address;
	@Field("ZIP Code")
	private Integer zip_code;
	@Field("X Coordinate")
	private Double x_coord;
	@Field("Y Coordinate")
	private Double y_coord;
	@Field("Ward")
	private Integer ward;
	@Field("Police District")
	private Integer police_district;
	@Field("Community Area")
	private Integer community_area;
	@Field("Latitude")
	private Double latitude;
	@Field("Longitude")
	private Double longitude;
	@Field("Location")
	private String location_desc;
	@Field("License Plate")
	private String license_plate;
	@Field("Vehicle Make/Model")
	private String vehicle_model;
	@Field("Vehicle Color")
	private String vehicle_color;
	@Field("Current Activity")
	private String current_activity;
	@Field("Most Recent Action")
	private String most_recent_action;
	@Field("How Many Days")
	private Integer how_many_days;
	@Field("SSA")
	private Integer ssa;
	@Field("Number of Black Carts Delivered")
	private Long number_of_black_carts_delivered;
	@Field("Surface")
	private String surface;
	@Field("Graffiti Location")
	private String graffiti_location;
	@Field("Potholes Filled")
	private Integer potholes_filled;
	@Field("Premises Baited")
	private Integer premises_baited;
	@Field("Premises With Garbage")
	private Integer premises_with_garbage;
	@Field("Premises With Rats")
	private Integer premises_with_rats;
	@Field("Nature of Violation")
	private String nature_of_violation;
	@Field("Debris Location")
	private String debris_location;
	@Field("Tree Location")
	private String tree_location;
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getCreation_date() {
		return creation_date;
	}
	public void setCreation_date(String creation_date) {
		this.creation_date = creation_date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCompletion_date() {
		return completion_date;
	}
	public void setCompletion_date(String completion_date) {
		this.completion_date = completion_date;
	}
	public String getService_request_number() {
		return service_request_number;
	}
	public void setService_request_number(String service_request_number) {
		this.service_request_number = service_request_number;
	}
	public String getType_of_service_request() {
		return type_of_service_request;
	}
	public void setType_of_service_request(String type_of_service_request) {
		this.type_of_service_request = type_of_service_request;
	}
	public String getStreet_address() {
		return street_address;
	}
	public void setStreet_address(String street_address) {
		this.street_address = street_address;
	}
	public Integer getZip_code() {
		return zip_code;
	}
	public void setZip_code(Integer zip_code) {
		this.zip_code = zip_code;
	}
	public Double getX_coord() {
		return x_coord;
	}
	public void setX_coord(Double x_coord) {
		this.x_coord = x_coord;
	}
	public Double getY_coord() {
		return y_coord;
	}
	public void setY_coord(Double y_coord) {
		this.y_coord = y_coord;
	}
	public Integer getWard() {
		return ward;
	}
	public void setWard(Integer ward) {
		this.ward = ward;
	}
	public Integer getPolice_district() {
		return police_district;
	}
	public void setPolice_district(Integer police_district) {
		this.police_district = police_district;
	}
	public Integer getCommunity_area() {
		return community_area;
	}
	public void setCommunity_area(Integer community_area) {
		this.community_area = community_area;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public String getLocation_desc() {
		return location_desc;
	}
	public void setLocation_desc(String location_desc) {
		this.location_desc = location_desc;
	}
	public String getLicense_plate() {
		return license_plate;
	}
	public void setLicense_plate(String license_plate) {
		this.license_plate = license_plate;
	}
	public String getVehicle_model() {
		return vehicle_model;
	}
	public void setVehicle_model(String vehicle_model) {
		this.vehicle_model = vehicle_model;
	}
	public String getVehicle_color() {
		return vehicle_color;
	}
	public void setVehicle_color(String vehicle_color) {
		this.vehicle_color = vehicle_color;
	}
	public String getCurrent_activity() {
		return current_activity;
	}
	public void setCurrent_activity(String current_activity) {
		this.current_activity = current_activity;
	}
	public String getMost_recent_action() {
		return most_recent_action;
	}
	public void setMost_recent_action(String most_recent_action) {
		this.most_recent_action = most_recent_action;
	}
	public Integer getHow_many_days() {
		return how_many_days;
	}
	public void setHow_many_days(Integer how_many_days) {
		this.how_many_days = how_many_days;
	}
	public Integer getSsa() {
		return ssa;
	}
	public void setSsa(Integer ssa) {
		this.ssa = ssa;
	}
	public Long getNumber_of_black_carts_delivered() {
		return number_of_black_carts_delivered;
	}
	public void setNumber_of_black_carts_delivered(Long number_of_black_carts_delivered) {
		this.number_of_black_carts_delivered = number_of_black_carts_delivered;
	}
	public String getSurface() {
		return surface;
	}
	public void setSurface(String surface) {
		this.surface = surface;
	}
	public String getGraffiti_location() {
		return graffiti_location;
	}
	public void setGraffiti_location(String graffiti_location) {
		this.graffiti_location = graffiti_location;
	}
	public Integer getPotholes_filled() {
		return potholes_filled;
	}
	public void setPotholes_filled(Integer potholes_filled) {
		this.potholes_filled = potholes_filled;
	}
	public Integer getPremises_baited() {
		return premises_baited;
	}
	public void setPremises_baited(Integer premises_baited) {
		this.premises_baited = premises_baited;
	}
	public Integer getPremises_with_garbage() {
		return premises_with_garbage;
	}
	public void setPremises_with_garbage(Integer premises_with_garbage) {
		this.premises_with_garbage = premises_with_garbage;
	}
	public Integer getPremises_with_rats() {
		return premises_with_rats;
	}
	public void setPremises_with_rats(Integer premises_with_rats) {
		this.premises_with_rats = premises_with_rats;
	}
	public String getNature_of_violation() {
		return nature_of_violation;
	}
	public void setNature_of_violation(String nature_of_violation) {
		this.nature_of_violation = nature_of_violation;
	}
	public String getDebris_location() {
		return debris_location;
	}
	public void setDebris_location(String debris_location) {
		this.debris_location = debris_location;
	}
	public String getTree_location() {
		return tree_location;
	}
	public void setTree_location(String tree_location) {
		this.tree_location = tree_location;
	}
	
	}