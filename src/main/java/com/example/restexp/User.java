package com.example.restexp;

import java.time.LocalDate;
import java.util.function.Predicate;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;


//@JsonIgnoreProperties(value = "name")
@JsonFilter("userfilter")
public class User{
	private Integer id;
	@Size( min=2 , message = "size should be min 2 char")
	
	//@JsonIgnore
	private String name;
	@Past(message = "Date should be in past")
	private LocalDate Birthdate;
	
	
	public User(int id, String name, LocalDate birthdate) {
		super();
		this.id = id;
		this.name = name;
		Birthdate = birthdate;
	}
	
	public User() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getBirthdate() {
		return Birthdate;
	}
	public void setBirthdate(LocalDate birthdate) {
		Birthdate = birthdate;
	}
	@Override
	public String toString() {
		return "Users [id=" + id + ", name=" + name + ", Birthdate=" + Birthdate + "]";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	
	
	
	

}
