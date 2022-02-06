package com.EY.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.EY.config.auth.CurrentUser;
import com.EY.config.auth.UserPrincipal;
import com.EY.exception.ResourceNotFoundException;
import com.EY.model.Todo;
import com.EY.service.TodoService;

/**
 * @author vinay
 *
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TodoController {

	@Autowired
	private TodoService todoService;

	private static final Logger logger = LogManager.getLogger(TodoController.class);

	public TodoController(TodoService todoService) {
		this.todoService = todoService;
	}

	/**
	 * Retrieve the TODO list by user ID based on currentUser and userId
	 * 
	 * @param currentUser
	 * @param userId
	 * @return
	 */
	@GetMapping("/users/{userId}/todos")
	public List<Todo> getTodoListByUserId(@CurrentUser UserPrincipal currentUser, @PathVariable String userId) {
		logger.info("Loading the TODO list of user id:" + userId);
		return todoService.todoListByUserId(currentUser.getId());
	}

	/**
	 * Retrieve the TODO by ID based on currentUser,userId and todoId
	 * 
	 * @param currentUser
	 * @param userId
	 * @param todoId
	 * @return
	 * @throws ResourceNotFoundException
	 */
	@GetMapping("users/{userId}/todos/{id}")
	public ResponseEntity<Todo> getTodoById(@CurrentUser UserPrincipal currentUser, @PathVariable String userId,
			@PathVariable(value = "id") Long todoId) throws ResourceNotFoundException {
		Todo todo = todoService.findById(todoId)
				.orElseThrow(() -> new ResourceNotFoundException("Todo not found for this id :: " + todoId));
		return ResponseEntity.ok().body(todo);
	}

	/**
	 * Create the TODO based on currentUser,userId and todo
	 * 
	 * @param currentUser
	 * @param userId
	 * @param todo
	 * @return
	 */
	@PostMapping("/users/{userId}/todos")
	public Todo createTodo(@CurrentUser UserPrincipal currentUser, @PathVariable String userId,
			@Valid @RequestBody Todo todo) {
		todo.setCreatedDate(new Date());
		logger.info("Creating the TODO list of user id:" + userId);
		return todoService.save(currentUser, todo);
	}

	/**
	 * Update the TODO based on currentUser,todoId and todoDetails
	 * 
	 * @param currentUser
	 * @param todoId
	 * @param todoDetails
	 * @return
	 * @throws ResourceNotFoundException
	 */
	@PutMapping("/users/{userId}/todos/{id}")
	public ResponseEntity<Todo> updateTodo(@CurrentUser UserPrincipal currentUser,
			@PathVariable(value = "id") Long todoId, @Valid @RequestBody Todo todoDetails)
			throws ResourceNotFoundException {
		Todo todo = todoService.findById(todoId)
				.orElseThrow(() -> new ResourceNotFoundException("Todo not found for this id :: " + todoId));

		todo.setDescription(todoDetails.getDescription());
		todo.setDone(todoDetails.isDone());
		todo.setTargetDate(todoDetails.getTargetDate());
		todo.setModifiedDate(new Date());
		logger.info("Updating the TODO list of todo id:" + todoId);

		final Todo updatedTodo = todoService.save(currentUser, todo);
		return ResponseEntity.ok(updatedTodo);
	}

	/**
	 * Delete the TODO based on currentUser and todoId
	 * 
	 * @param currentUser
	 * @param todoId
	 * @return
	 * @throws ResourceNotFoundException
	 */
	@DeleteMapping("/users/{userId}/todos/{id}")
	public Map<String, Boolean> deleteTodo(@CurrentUser UserPrincipal currentUser,
			@PathVariable(value = "id") Long todoId) throws ResourceNotFoundException {
		Todo todo = todoService.findById(todoId)
				.orElseThrow(() -> new ResourceNotFoundException("Todo not found for this id :: " + todoId));
		logger.info("Deleting the TODO list of todo id:" + todoId);

		todoService.delete(todo);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
