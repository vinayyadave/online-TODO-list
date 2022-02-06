package com.EY.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.EY.model.Todo;

/**
 * @author vinay
 *
 */
@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

	/**
	 * @param user
	 * @return
	 */
	List<Todo> findAllByUserId(Long user);
}
