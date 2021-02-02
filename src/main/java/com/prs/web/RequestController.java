package com.prs.web;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
@RequestMapping("/request")
public class RequestController {

	
@Autowired
private RequestRepo requestRepo;


@GetMapping("/")
private List <Request> getAllRequests() {
	return requestRepo.findAll();
}


@GetMapping("/{id}")
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

@DeleteMapping("/{id}")
private Request deleteLineItem(@PathVariable int id) {
	Optional <Request> l = requestRepo.findById(id);
	if(l.isPresent()) {
		requestRepo.deleteById(id);
	} else {
	System.out.println("cannot find" + id + "to delete");
	}
return l.get();
}

@PutMapping("/rejected")
private Request rejectRequest(@RequestBody Request r) {
	r.setStatus("Rejected");
	return requestRepo.save(r);
}

@PutMapping("/approved")
private Request approveRequest(@RequestBody Request r) {
	r.setStatus("Approved");
	return requestRepo.save(r);
}


@PutMapping("/submit-review")
private Request requestStatus(@RequestBody Request r) {
	double total = r.getTotal();
	if(total <= 50.00) {
		r.setStatus("approved");
	} else {
		r.setStatus("review");
	}
	r.setSubmittedDate(LocalDateTime.now());
	return requestRepo.save(r);
}

@GetMapping("/list-review/{id}")
private List<Request> listItemsInReview(@PathVariable int id) {
	return requestRepo.findByUserIdNotAndStatus(id, "review");
}


}
