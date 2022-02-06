package com.EY.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EY.config.auth.UserPrincipal;
import com.EY.model.Todo;
import com.EY.model.User;
import com.EY.repository.TodoRepository;
import com.EY.repository.UserRepository;

/**
 * @author vinay
 *
 */
@Service
public class TodoServiceImpl implements TodoService {

	@Autowired
	private TodoRepository todoRepository;

	@Autowired
	private UserRepository userRepository;
	
	private static final Logger logger = LogManager.getLogger(TodoServiceImpl.class);


	@Override
	public List<Todo> todoListByUserId(Long userId) {
		logger.info("Finding all by user id:" + userId);
		return todoRepository.findAllByUserId(userId);
	}

	@Override
	public Optional<Todo> findById(Long todoId) {
		return todoRepository.findById(todoId);
	}

	@Override
	public Todo save(UserPrincipal currentUser, Todo todo) {
		Optional<User> user = userRepository.findById(currentUser.getId());
		todo.setUser(user.get());

		return todoRepository.save(todo);
	}

	@Override
	public void delete(Todo todo) {
		todoRepository.delete(todo);
	}

}
