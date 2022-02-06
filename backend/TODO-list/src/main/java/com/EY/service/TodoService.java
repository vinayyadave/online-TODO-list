package com.EY.service;

import java.util.List;
import java.util.Optional;

import com.EY.config.auth.UserPrincipal;
import com.EY.model.Todo;

public interface TodoService {
	
	List<Todo> todoListByUserId(Long userId);

	Optional<Todo> findById(Long todoId);

	Todo save(UserPrincipal currentUser, Todo todo);

	void delete(Todo todo);

}
