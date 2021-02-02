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

import com.prs.business.Product;
import com.prs.business.User;
import com.prs.db.ProductRepo;

@CrossOrigin
@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductRepo productRepo;
	
	@GetMapping("/")
	private List <Product> getAllProducts() {
		return productRepo.findAll();
	}
	
	@GetMapping("{/id}")
	private Optional <Product> getProductById(@PathVariable int id) {
		return productRepo.findById(id);
	}
	
	@PostMapping("/")
	private Product postProduct(@RequestBody Product p) {
		p = productRepo.save(p);
		return p;
	}
	
	@PutMapping("/")
	private Product putProduct(@RequestBody Product p) {
		p = productRepo.save(p);
		return p;
	}
	
	@DeleteMapping("/{id}")
	private Product deleteProduct(@PathVariable int id) {
		Optional<Product> p = productRepo.findById(id);
		if(p.isPresent()) {
		productRepo.deleteById(id);
		} else {
			System.out.println("there is no" + id + "to delete");
		}
		return p.get();
	}
}
