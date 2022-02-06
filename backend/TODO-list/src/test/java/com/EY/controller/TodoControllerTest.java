package com.EY.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.EY.config.auth.UserPrincipal;
import com.EY.model.Todo;
import com.EY.service.TodoService;
import com.EY.service.TodoServiceImpl;

/**
 * @author vinay
 *
 */
public class TodoControllerTest {


	@Mock
	TodoService todoService;
	
	TodoController todoController;
	
	@Mock
	UserPrincipal currentUser;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		todoService = new TodoServiceImpl();
		currentUser = new UserPrincipal(10L, "test", "test", "Test");
		
		todoController = new TodoController(todoService);
	}

	@Test
	@Ignore
	public void getTodoListByUserId() {
		Todo todo = new Todo();
		List<Todo> todoData = new ArrayList<>();
				todoData.add(todo);
		String userId = "10";
		//when(currentUser.getId()).thenReturn(10L);
		when(todoService.todoListByUserId(currentUser.getId())).thenReturn(todoData);
		
		List<Todo> todos = todoController.getTodoListByUserId(null, userId);
		assertEquals(1, todos.size());
		
	}

}