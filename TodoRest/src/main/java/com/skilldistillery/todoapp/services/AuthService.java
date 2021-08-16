package com.skilldistillery.todoapp.services;

import com.skilldistillery.todo.entities.User;

public interface AuthService {
	
	public User register(User user); // this takes a new User and adds their account 

}
