package com.quickgrab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quickgrab.model.FoodModel;

import com.quickgrab.service.FoodService;

@RestController
@RequestMapping("/food")
public class FoodController {
	
	@Autowired
	FoodService foodService;
	
	@PostMapping
	public ResponseEntity<FoodModel> createFood(@RequestBody FoodModel food){
		return foodService.createFood(food);
	}
	///vfffvvf

}
