package com.example.restexp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
	
	
	
	public static List<User> users= new ArrayList();
	
	
	private static int usercount=0;
	
	
	
	static {
		
			users.add(new User(++usercount, "james", LocalDate.now().minusYears(30)));

			users.add(new User(++usercount, "jenny", LocalDate.now().minusYears(20)));
		

			users.add(new User(++usercount, "evi", LocalDate.now().minusYears(10)));
		
	}
	
	
	public List<User> findall()
	{
		return users;
	}
	
	


	public User finduser(int id) {
		Predicate<? super User> predicate = user -> user.getId().equals(id);
		// TODO Auto-generated method stub
		return users.stream().filter(predicate).findFirst().orElse(null);
	}




	public User save(User user) {
		user.setId(++usercount);
		users.add(user);
		//return user;
		return user;

	}


	public void deleteuser(int id) {
		// TODO Auto-generated method stub
		Predicate<? super User> predicate = user -> user.getId().equals(id);
		users.removeIf(predicate);
	}

	
	

	


}
