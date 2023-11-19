package com.example.restexp.user;

import java.time.LocalDate;
import java.util.function.Predicate;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

@Entity(name="user_details")
public class User{
	
	@Id
	@GeneratedValue
	private Integer id;
	@Size( min=2 , message = "size should be min 2 char")
	
	//@JsonIgnore
	private String name;
	@Past(message = "Date should be in past")
	private LocalDate birthdate;
	
	
	public User(int id, String name, LocalDate birthdate) {
		super();
		this.id = id;
		this.name = name;
		birthdate = birthdate;
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
		return birthdate;
	}
	public void setBirthdate(LocalDate birthdate) {
		birthdate = birthdate;
	}
	@Override
	public String toString() {
		return "Users [id=" + id + ", name=" + name + ", Birthdate=" + birthdate + "]";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	
	
	
	

}
