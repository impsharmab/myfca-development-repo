package com.imperialm.imiservices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.imperialm.imiservices.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	@Query("select u from User u where u.userId=?1 and u.delflag = 'N'")
	User findByUserId(String userId);
	@Query("select u from User u where u.email=?1 and u.delflag = 'N'")
	User findByEmail(String email);
}
