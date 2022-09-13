package com.quickgrab.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.quickgrab.exception.ResourceNotFoundException;
import com.quickgrab.model.FoodModel;
import com.quickgrab.model.RestaurantModel;
import com.quickgrab.repository.FoodRepo;
import com.quickgrab.repository.RestaurantRepo;

@Service
public class RestaurantService {

	@Autowired
	RestaurantRepo restaurantRepo;
	@Autowired
	FoodRepo foodRepo;

	public ResponseEntity<RestaurantModel> addFood(Integer id, FoodModel foodModel) {

		RestaurantModel restaurant = restaurantRepo.findById(id).orElse(null);
		restaurant.getFoodModel().add(foodModel);
		foodRepo.save(foodModel);
		restaurantRepo.save(restaurant);
		return new ResponseEntity<RestaurantModel>(restaurant, HttpStatus.ACCEPTED);

	}

	public ResponseEntity<RestaurantModel> signUpRestaurant(RestaurantModel restaurant) {

		restaurantRepo.save(restaurant);
		return new ResponseEntity<RestaurantModel>(restaurant, HttpStatus.ACCEPTED);

	}

	public FoodModel deletingFood(int rid, int fid) throws ResourceNotFoundException {

		if (!restaurantRepo.existsById(rid)) {

			throw new ResourceNotFoundException("Restaurant Id not found");

		} else {

			if (!foodRepo.existsById(fid)) {

				throw new ResourceNotFoundException("food Id not found");

			}

			foodRepo.deleteById(fid);

		}

		return null;

	}

	// Update food by Id

	public FoodModel updateFood(int rid, FoodModel foodModel, int fid) throws ResourceNotFoundException {

		if (!restaurantRepo.existsById(rid)) {

			throw new ResourceNotFoundException("Restaurant Id not found");

		}

		return foodRepo.findById(fid).map(food -> {

			food.setFoodName(foodModel.getFoodName());

			food.setFoodPrice(foodModel.getFoodPrice());

			food.setFood_type(foodModel.getFood_type());

			return foodRepo.save(food);

		}).orElseThrow(() -> new ResourceNotFoundException("food id not found"));

	}

	public ResponseEntity<List<FoodModel>> showFoodItems(Integer restId) throws ResourceNotFoundException {

		if (!restaurantRepo.existsById(restId)) {
			throw new ResourceNotFoundException("Restaurant Id not found");
		}

		RestaurantModel restaurantModel = restaurantRepo.findById(restId).orElse(null);
		return new ResponseEntity<List<FoodModel>>(restaurantModel.getFoodModel(), HttpStatus.ACCEPTED);

	}

}
