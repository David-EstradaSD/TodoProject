package com.skilldistillery.todoapp.controllers;

import java.security.Principal;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.todo.entities.Todo;
import com.skilldistillery.todoapp.repositories.TodoRepository;
import com.skilldistillery.todoapp.services.TodoService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost:4201"}) // this is for our ngTodo angular app which is served on port 4201
public class TodoController {

	@Autowired
	private TodoService todoService;

	@Autowired
	TodoRepository todoRepo;
	
//	private String username = "shaun";
	
	@GetMapping("todos")
	public Set<Todo> index(HttpServletRequest req, HttpServletResponse res,Principal principal) {
//		return todoService.index(username);	
		return todoService.index(principal.getName());
	}

	@GetMapping("todos/{tid}")
	public Todo show(
			HttpServletRequest req, 
			HttpServletResponse res, 
			@PathVariable int tid,
			Principal principal
			) {
		Todo todo = todoService.show(principal.getName(), tid);
		if (todo == null) {
			res.setStatus(404); // Not Found
		}
		return todo;
	}

	@PostMapping("todos")
	public Todo create(
			HttpServletRequest req, 
			HttpServletResponse res, 
			@RequestBody Todo todo, 
			Principal principal) {
		todo = todoService.create(principal.getName(), todo);
		try {
			if (todo == null) {
				res.setStatus(404); 
			} else {
				res.setStatus(201); // Created
				StringBuffer url = req.getRequestURL();
				url.append("/").append(todo.getId());
				res.setHeader("Location", url.toString());
			}
		} catch (Exception e) {
			res.setStatus(400); // Bad Request 
			todo = null;
		}
		return todo;
	}

	@PutMapping("todos/{tid}")
	public Todo update(
			HttpServletRequest req, 
			HttpServletResponse res,
			@PathVariable int tid, 
			@RequestBody Todo todo,
			Principal principal) {
		try {
			todo = todoService.update(principal.getName(), tid, todo);
		} catch (Exception e) {
			res.setStatus(400);
			todo = null;
		}
		if (todo == null) {
			res.setStatus(404);
		}
		return todo;
	}

	@DeleteMapping("todos/{tid}")
	public void destroy(HttpServletRequest req, HttpServletResponse res, @PathVariable int tid, Principal principal) {
		try {
			boolean deleted = todoService.destroy(principal.getName(), tid);
			if (deleted) {
				res.setStatus(204); // No Content
			} else {
				res.setStatus(404);
			}
		} catch (Exception e) {
			res.setStatus(400); 
		}
	}

}
