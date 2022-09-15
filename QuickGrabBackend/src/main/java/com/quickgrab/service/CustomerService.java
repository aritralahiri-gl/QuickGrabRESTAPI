package com.quickgrab.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quickgrab.dto.RestaurantFood;
import com.quickgrab.exception.ResourceNotFoundException;
import com.quickgrab.model.CustomerModel;
import com.quickgrab.model.FoodModel;

import com.quickgrab.repository.CustomerRepo;
import com.quickgrab.repository.FoodRepo;


@Service
public class CustomerService {

	@Autowired
	private CustomerRepo customerRepo;
	@Autowired

	private FoodRepo foodRepo;

	

	public CustomerModel signUpCustomer(CustomerModel customer) {

		return customerRepo.save(customer);

	}

	public List<FoodModel> getallfood()

	{
		return foodRepo.findAll();
	}

	public List<RestaurantFood> getJoinInformation(Integer id) throws ResourceNotFoundException{



		 if(!customerRepo.existsById(id)) {

		  throw new ResourceNotFoundException("Food Id not found");

		 }

		 return customerRepo.getJoinInformation(id);

		 }

}
