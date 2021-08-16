package com.skilldistillery.todoapp.services;

import java.util.Set;

import com.skilldistillery.todo.entities.Todo;

public interface TodoService {

	// Todos belong to a specific User, so we use the unique Username
	//  to manipulate the appropriate Todos 
	  
		public Set<Todo> index(String username);

	    public Todo show(String username, int tid);

	    public Todo create(String username, Todo todo);

	    public Todo update(String username, int tid, Todo todo);

	    public boolean destroy(String username, int tid);

	
}
