package com.quickgrab.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.quickgrab.exception.ResourceNotFoundException;
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

	public ResponseEntity<OrderModel> addFoodToOrder(Integer foodId, Integer orderId) {

		OrderModel orderModel = orderRepo.findById(orderId).orElse(null);
		FoodModel foodModel = foodRepo.findById(foodId).orElse(null);
		float orderPrice = orderModel.getOrderPrice();
		orderModel.setOrderPrice(orderPrice + foodModel.getFoodPrice());
		orderModel.getFoodModel().add(foodModel);
		return new ResponseEntity<OrderModel>(orderRepo.save(orderModel), HttpStatus.ACCEPTED);
	}

	public ResponseEntity<List<OrderModel>> showAllOrders() {

		List<OrderModel> orderModel = orderRepo.findAll();

		return new ResponseEntity<List<OrderModel>>(orderModel, HttpStatus.ACCEPTED);
	}

	public OrderModel deleteOrder(Integer orderId) throws ResourceNotFoundException {

		if (!orderRepo.existsById(orderId)) {

			throw new ResourceNotFoundException("Order Id not found");

		} else {

			orderRepo.deleteById(orderId);

		}

		return null;
	}

}
