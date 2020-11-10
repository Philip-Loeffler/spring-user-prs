package com.prs.web;

import java.util.List;
import java.util.Optional;

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

import com.prs.business.User;
import com.prs.db.UserRepo;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

	
	@Autowired
	private UserRepo userRepo;
		
	@GetMapping("/")
	public List<User> getAll() {
		//find all is doing a select all query, and literally doing everything for you. like opening connection ectect
		return userRepo.findAll();
}
	@GetMapping("/{id}")
public Optional <User> getUserById(@PathVariable int id) {
		return userRepo.findById(id);
	}
	
	@PostMapping("/")
	public User addUser(@RequestBody User u) {
		u = userRepo.save(u);
		return u;
	}
	
	@PutMapping("/")
	public User updateUser(@RequestBody User u) {
		u = userRepo.save(u);
		return u;
	}
	
	@DeleteMapping("/{id}")
	public User deleteUser(@PathVariable int id,  User m) {
		Optional<User> u = userRepo.findById(id);
		if(u.isPresent()) {
			userRepo.deleteById(id);
		} else {
			System.out.println("error trying to delte id" + id);
		}
		return u.get();
}
}
