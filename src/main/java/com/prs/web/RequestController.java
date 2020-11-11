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

import com.prs.business.Request;
import com.prs.db.RequestRepo;

@CrossOrigin
@RestController
@RequestMapping("/requests")
public class RequestController {

	
@Autowired
private RequestRepo requestRepo;


@GetMapping("/")
private List <Request> getAllRequests() {
	return requestRepo.findAll();
}


@GetMapping("{/id}")
private Optional <Request> getRequestId(@PathVariable int id) {
	return requestRepo.findById(id);
}
	

@PostMapping("")
private Request postRequest(@RequestBody Request r) {
	return requestRepo.save(r);
}

@PutMapping("")
private Request putRequest(@RequestBody Request r) {
	return requestRepo.save(r);
}

@DeleteMapping("{/id}")
private Request deleteLineItem(@PathVariable int id) {
	Optional <Request> l = requestRepo.findById(id);
	if(l.isPresent()) {
		requestRepo.deleteById(id);
	} else {
	System.out.println("cannot find" + id + "to delete");
	}
return l.get();
}
}
