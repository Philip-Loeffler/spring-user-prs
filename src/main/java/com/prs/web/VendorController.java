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

import com.prs.business.Vendor;
import com.prs.db.VendorRepo;


@CrossOrigin
@RestController
@RequestMapping("/vendor")
public class VendorController {

	
	@Autowired
	private VendorRepo vendorRepo;
	
	@GetMapping("") 
	private List <Vendor> getAllVendors() {
		return vendorRepo.findAll();
	}
	
	@GetMapping("/{id}")
	private  Optional <Vendor> getVendorById(@PathVariable int id) {
		return vendorRepo.findById(id);
	}
	
	@PostMapping("/")
	private Vendor postVendor(@RequestBody Vendor v) {
		return vendorRepo.save(v);
	}
	
	@PutMapping("/")
	private Vendor putVendor(@RequestBody Vendor v) {
		return vendorRepo.save(v);
	}
	
	@DeleteMapping("/{id}")
	private Vendor deleteVendor(@PathVariable int id) {
		Optional<Vendor> v = vendorRepo.findById(id);
if(v.isPresent() ) {
	vendorRepo.deleteById(id);
	} else {
		System.out.println("there is no" + id + "to delete");
	}
	return v.get();
}

}
