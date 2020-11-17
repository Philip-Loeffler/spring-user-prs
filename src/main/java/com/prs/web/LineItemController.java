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

import com.prs.business.LineItem;
import com.prs.business.Product;
import com.prs.business.Request;
import com.prs.db.LineItemRepo;
import com.prs.db.RequestRepo;

@CrossOrigin
@RestController
@RequestMapping("/lineItems")
public class LineItemController {

	
@Autowired
private LineItemRepo lineItemRepo;
private RequestRepo requestRepo;

@GetMapping("/")
private List <LineItem> getAllLineItems() {
	return lineItemRepo.findAll();
}


@GetMapping("/{id}")
private Optional <LineItem> getLineItemById(@PathVariable int id) {
	return lineItemRepo.findById(id);
}

@PostMapping("/")
private LineItem postLineItem(@RequestBody LineItem l) {
	recalculateTotal(l.getRequest().getId());
	return lineItemRepo.save(l);
}

@PutMapping("/")
private LineItem putLineItem(@RequestBody LineItem l) {
	recalculateTotal(l.getRequest().getId());
	return lineItemRepo.save(l);
}

@DeleteMapping("{/id}")
private LineItem deleteLineItem(@PathVariable int id) {
	Optional <LineItem> l = lineItemRepo.findById(id);
	LineItem line = new LineItem();
	if(l.isPresent()) {
		lineItemRepo.deleteById(id);
	} else {
	System.out.println("cannot find" + id + "to delete");
	}
	recalculateTotal(line.getRequest().getId());
return l.get();
}

@GetMapping("lines-items-for-pr/{id}")
private List<LineItem> linesItemsForRequest(@PathVariable int id) {
	return lineItemRepo.findAllByRequestId(id);
	}


//refactor
public void recalculateTotal(int requestId) {
	List<LineItem> line = lineItemRepo.findByRequestId(requestId);	
	double total = 0.0;
	for (LineItem lineItems : line) {
		Product p = lineItems.getProduct();
		total += p.getPrice()*lineItems.getQuantity();
	}
	Request r = requestRepo.findById(requestId).get();
	r.setTotal(total);
	requestRepo.save(r);
	}
}

