package com.EY.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.EY.model.Role;
import com.EY.model.RoleName;

/**
 * @author vinay
 *
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	
	/**
	 * @param roleName
	 * @return
	 */
	Optional<Role> findByName(RoleName roleName);
}
