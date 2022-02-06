package com.EY.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.EY.model.User;

/**
 * @author vinay
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	/**
	 * @param username
	 * @return
	 */
	Optional<User> findByUsername(String username);

	/**
	 * Find the user
	 * 
	 * @param email
	 * @return
	 */
	Optional<User> findByEmail(String email);

	/**
	 * @param username
	 * @param email
	 * @return
	 */
	Optional<User> findByUsernameOrEmail(String username, String email);

	/**
	 * @param userIds
	 * @return
	 */
	List<User> findByIdIn(List<Long> userIds);

	/**
	 * @param username
	 * @return
	 */
	Boolean existsByUsername(String username);

	/**
	 * @param email
	 * @return
	 */
	Boolean existsByEmail(String email);
}
