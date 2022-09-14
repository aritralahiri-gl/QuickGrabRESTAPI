package com.quickgrab.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quickgrab.dto.RestaurantFood;
import com.quickgrab.model.CustomerModel;
import com.quickgrab.model.FoodModel;

import com.quickgrab.repository.CustomerRepo;
import com.quickgrab.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private CustomerRepo customerRepo;

	@PostMapping
	public CustomerModel signUpCustomer(@RequestBody CustomerModel customer) {
		return customerService.signUpCustomer(customer);
	}

	@GetMapping("/foodlist")

	public List<FoodModel> getFood() {

		return customerService.getallfood();

	}

	@GetMapping("/getFood/{id}")

	public List<RestaurantFood> getJointInformation(@PathVariable Integer id) {

		return customerService.getJoinInformation(id);

	}
}
