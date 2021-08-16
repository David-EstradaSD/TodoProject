package com.skilldistillery.todoapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.skilldistillery.todo.entities.User;
import com.skilldistillery.todoapp.repositories.UserRepository;

@Service
public class AuthServiceImpl implements AuthService {
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public User register(User user) {
		
//		encrypt and set the password for the User.
		user.setPassword(encoder.encode(user.getPassword())); // take the user password from the front end, reassign it to the encoded version
		
//		set the enabled field of the object to true.
		user.setEnabled(true);
		
//		set the role field of the object to "standard".
		user.setRole("standard user"); // this is usually arbitrary unless its like CMS with Admin / Contractor roles 
		
//		saveAndFlush the user using the UserRepo.
		userRepo.saveAndFlush(user);
		
//		return the User object.
		return user;
	}

}
