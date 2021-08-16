package com.skilldistillery.todoapp.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.todo.entities.Todo;

public interface TodoRepository extends JpaRepository<Todo, Integer> {
	
	Set<Todo> findByUser_Username(String username);
	
	Todo findByIdAndUser_Username(int todoId, String username);
	

}