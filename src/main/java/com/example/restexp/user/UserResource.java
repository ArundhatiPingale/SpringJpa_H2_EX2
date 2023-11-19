package com.example.restexp.user;



import java.net.URI;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

import com.example.restexp.jpa.PostRepository;
import com.example.restexp.jpa.UserRepository;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import jakarta.validation.Valid;

@RestController
public class UserResource {
	
	
	
	private UserRepository repository;
	private PostRepository postrepository;
	
	
	public UserResource(UserRepository repository, PostRepository postrepository) {
		super();
		
		this.repository=repository;
		this.postrepository=postrepository;
	}


    @GetMapping("/jpa/users")
	public List<User> Retrivealluser()
	{
   return repository.findAll();
    	
	}
    
    @GetMapping("/jpa/users/{id}")
    public User Retriveuser(@PathVariable int id)
	{
		Optional<User> user= repository.findById(id);
		
		 if (user.isEmpty())
			 throw new UserNotFoundException(" user not found id:" +id);
		
			
		 return user.get();
		
	}    
    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> RetriveuserPosts(@PathVariable int id)
	{
		Optional<User> user= repository.findById(id);
		
		 if (user.isEmpty())
			 throw new UserNotFoundException(" user not found id:" +id);
		
			
		 return user.get().getPosts();
		
	}

    
    

    @DeleteMapping("/jpa/users/{id}")
	public void Deleteuser(@PathVariable int id)
	{
    	repository.deleteById(id);
		
		
		
	}
    @PostMapping("/jpa/users")
   	public ResponseEntity<User> CreateUser( @Valid @RequestBody User user)
   	{
   		 User saveduser = repository.save(user);
   		 
   		 URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saveduser.getId()).toUri();
   		 
   		return  ResponseEntity.created(location).build();
   		
   	}
    
    
    @PostMapping("/jpa/users/{id}/posts")
   	public ResponseEntity<Post> CreateUserwithPost(@PathVariable int id , @Valid @RequestBody Post post)
   	{
   		 Optional<User> user = repository.findById(id);
   		 
   		 
   		 post.setUser(user.get());
   		Post savedpost= postrepository.save(post);
		 URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedpost.getId()).toUri();
   		 
	   		return  ResponseEntity.created(location).build();
	   		
   		 
   		 
   		 
   		 
   		  	}
}
