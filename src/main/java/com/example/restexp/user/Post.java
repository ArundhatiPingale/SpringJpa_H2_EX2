package com.example.restexp.user;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Post {
	@Id
	@GeneratedValue
	private Integer id;
	
	@ManyToOne( fetch =  FetchType.LAZY)
	@JsonIgnore
	private User user;
	
	private String description;

	
	protected Post()
	{
		
	}
	
	public Post(int id,  String description) {
		super();
		this.id = id;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public String getDescription() {
		return description;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	

}
