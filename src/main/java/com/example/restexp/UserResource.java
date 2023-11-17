package com.example.restexp;



import java.net.URI;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import jakarta.validation.Valid;

@RestController
public class UserResource {
	
	@Autowired
	private UserDaoService userdaoservice;
	
	
	
	public UserResource(UserDaoService userdaoservice) {
		super();
		this.userdaoservice = userdaoservice;
	}


    @GetMapping("/Filtering")
	public MappingJacksonValue Retrivealluser()
	{
    	List<User> userslist=Arrays.asList(new User(1 , "arun", LocalDate.now()),
    			new User(2 , "aru", LocalDate.now()));

    	MappingJacksonValue MappingJacksonValue= new 	MappingJacksonValue(userslist);
    	
    	SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("name");
    	
    	FilterProvider filters= new SimpleFilterProvider().addFilter("userfilter", filter);
    	
    	MappingJacksonValue.setFilters(filters);
    	
    	
		//return userdaoservice.findall();
    	return MappingJacksonValue;
	}
    
    @GetMapping("/users/{id}")
    
	public User Retriveuser(@PathVariable int id)
	{
		User user= userdaoservice.finduser(id);
		
		 if (user==null)
			 throw new UserNotFoundException(" user not found id:" +id);
		
			
		 return user;
		
	}

    @DeleteMapping("/users/{id}")
	public void Deleteuser(@PathVariable int id)
	{
		userdaoservice.deleteuser(id);
		
		
		
	}
    @PostMapping("/users")
   	public ResponseEntity<User> CreateUser( @Valid @RequestBody User user)
   	{
   		 User saveduser = userdaoservice.save(user);
   		 
   		 URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saveduser.getId()).toUri();
   		 
   		return  ResponseEntity.created(location).build();
   		
   	}
}
