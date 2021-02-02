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
@RequestMapping("/lineItem")
public class LineItemController {

	
@Autowired
private LineItemRepo lineItemRepo;

@Autowired
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
//	recalculateTotal(l.getRequest().getId());
	l = lineItemRepo.save(l);
	recalculateLineItemTotal(l);
	return l;
	
}

@PutMapping("/")
private LineItem putLineItem(@RequestBody LineItem l) {
	l = lineItemRepo.save(l);
	recalculateLineItemTotal(l);
	return l;
	
}

@DeleteMapping("/{id}")
private LineItem deleteLineItem(@PathVariable int id) {
	Optional <LineItem> l = lineItemRepo.findById(id);
	if(l.isPresent()) {
		lineItemRepo.deleteById(id);
		recalculateLineItemTotal(l.get());
	} else {
	System.out.println("cannot find" + id + "to delete");
	}
return l.get();
}

@GetMapping("line-item-for-pr/{id}")
private List<LineItem> linesItemsForRequest(@PathVariable int id) {
	return lineItemRepo.findAllByRequestId(id);
	}


public void recalculateLineItemTotal(LineItem li) {
	List <LineItem> l = lineItemRepo.findByRequestId(li.getRequest().getId());
	double total = 0.0;
	for (LineItem lineItem : l) {
		Product p = lineItem.getProduct();
		total += (p.getPrice() * lineItem.getQuantity());
	}
	Request request = li.getRequest();
	request.setTotal(total);
	requestRepo.save(request);
	
}



	

}




