package com.example.restexp;



import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;

@RestController
public class UserResource {
	
	@Autowired
	private UserDaoService userdaoservice;
	
	
	
	public UserResource(UserDaoService userdaoservice) {
		super();
		this.userdaoservice = userdaoservice;
	}


    @GetMapping("/users")
	public List<User> Retrivealluser()
	{
		return userdaoservice.findall();
		
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
