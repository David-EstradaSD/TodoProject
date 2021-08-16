package com.skilldistillery.todoapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.todo.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	User findByUsername(String username);

}
