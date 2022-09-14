package com.quickgrab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.quickgrab.model.FoodModel;
import com.quickgrab.model.OrderModel;
import com.quickgrab.repository.FoodRepo;
import com.quickgrab.repository.OrderRepo;

@Service
public class OrderService {

	@Autowired
	private OrderRepo orderRepo;
	@Autowired
	private FoodRepo foodRepo;

	public ResponseEntity<OrderModel> createOrder(Integer custId, OrderModel orderModel) {

		orderModel.setCustId(custId);

		return new ResponseEntity<OrderModel>(orderRepo.save(orderModel), HttpStatus.ACCEPTED);
	}

	public ResponseEntity<OrderModel> addFoodToOrder(Integer orderId, Integer foodId) {

		OrderModel orderModel = orderRepo.findById(orderId).orElse(null);
		FoodModel foodModel = foodRepo.findById(foodId).orElse(null);
		
//		orderModel.getFoodModel().add(foodModel);
		return new ResponseEntity<OrderModel>(orderRepo.save(orderModel), HttpStatus.ACCEPTED);
	}

}
