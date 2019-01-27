package com.chicago.nosql.models;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Citizens")
public class Citizen {

	@Id
	private String _id;
	
	private String name;
	private String surname;
	private String address;
	private String telephone;
	private List<String> upvotes;
	private Integer upvote_count;
	
	
	public Integer getUpvote_count() {
		return upvote_count;
	}
	public void setUpvote_count(Integer upvote_count) {
		this.upvote_count = upvote_count;
	}
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public List<String> getUpvotes() {
		return upvotes;
	}
	public void setUpvotes(List<String> upvotes) {
		this.upvotes = upvotes;
	}
	
	
	
}
