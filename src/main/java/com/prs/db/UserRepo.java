package com.prs.db;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prs.business.User;

public interface UserRepo extends JpaRepository<User, Integer>{

	// you only want to return one user, thats why we dont have a list
	Optional<User> findByUserNameAndPassword(String userName, String password);

}




