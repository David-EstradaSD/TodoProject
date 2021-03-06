package com.skilldistillery.todoapp.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.todo.entities.Todo;
import com.skilldistillery.todo.entities.User;
import com.skilldistillery.todoapp.repositories.TodoRepository;
import com.skilldistillery.todoapp.repositories.UserRepository;

@Service
public class TodoServiceImpl implements TodoService {
	
	@Autowired
	private TodoRepository todoRepo;
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public Set<Todo> index(String username) {
		return todoRepo.findByUser_Username(username);
	}

	@Override
	public Todo show(String username, int tid) {
		return todoRepo.findByIdAndUser_Username(tid, username);
	}

	@Override
	public Todo create(String username, Todo todo) {
		User user = userRepo.findByUsername(username);
		todo.setUser(user);
		try {
			todoRepo.saveAndFlush(todo);
		} catch (Exception e) {
			todo = null;
		}
		return todo;
	}

	@Override
	public Todo update(String username, int tid, Todo todo) {
		Todo managed = todoRepo.findByIdAndUser_Username(tid, username);
		if (managed != null) {
			managed.setTask(todo.getTask());
			managed.setDescription(todo.getDescription());
			managed.setCompleted(todo.isCompleted());
			managed.setDueDate(todo.getDueDate());
			managed.setCompleteDate(todo.getCompleteDate());
			todoRepo.saveAndFlush(managed);
		}
		return managed;
	}

	@Override
	public boolean destroy(String username, int tid) {
		
		boolean deleted = false;
		Todo todo = todoRepo.findByIdAndUser_Username(tid, username);
		if (todo != null) {
			todoRepo.delete(todo);
			deleted = true;
		}
		return deleted;
	}

}
