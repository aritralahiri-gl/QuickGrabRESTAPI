package com.quickgrab.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quickgrab.dto.RestaurantFood;

import com.quickgrab.model.CustomerModel;
import com.quickgrab.model.FoodModel;
import com.quickgrab.model.RestaurantModel;
import com.quickgrab.repository.CustomerRepo;
import com.quickgrab.repository.FoodRepo;
import com.quickgrab.repository.RestaurantRepo;

@Service
public class CustomerService {

	@Autowired
	CustomerRepo customerRepo;
	@Autowired

	FoodRepo foodRepo;

	@Autowired
	RestaurantRepo restaurantRepo;

	public CustomerModel signUpCustomer(CustomerModel customer) {

		return customerRepo.save(customer);

	}

	public List<FoodModel> getallfood()

	{
		return foodRepo.findAll();
	}

	public RestaurantModel getFoodById(Integer foodId) {

		FoodModel foodModel = foodRepo.findById(foodId).orElse(null);
		RestaurantModel restaurantModel = restaurantRepo.findById(foodModel.getRestId()).orElse(null);

		return restaurantModel;
	}

	public List<RestaurantFood> getJoinInformation(Integer id) {

		return customerRepo.getJoinInformation(id);

	}

}
