package com.quickgrab.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.quickgrab.exception.ResourceNotFoundException;
import com.quickgrab.model.FoodModel;
import com.quickgrab.model.OrderModel;
import com.quickgrab.repository.CustomerRepo;
import com.quickgrab.repository.FoodRepo;
import com.quickgrab.repository.OrderRepo;

@Service
public class OrderService {

	@Autowired
	private OrderRepo orderRepo;
	@Autowired
	private FoodRepo foodRepo;
	@Autowired
	private CustomerRepo customerRepo;

	public ResponseEntity<OrderModel> createOrder(Integer custId, OrderModel orderModel)
			throws ResourceNotFoundException {

		if (!customerRepo.existsById(custId))
			throw new ResourceNotFoundException("Customer Id not found");

		orderModel.setCustId(custId);

		return new ResponseEntity<OrderModel>(orderRepo.save(orderModel), HttpStatus.ACCEPTED);
	}

	public ResponseEntity<OrderModel> addFoodToOrder(Integer foodId, Integer orderId) throws ResourceNotFoundException {

		if (!orderRepo.existsById(orderId))
			throw new ResourceNotFoundException("Order Id not found");

		if (!foodRepo.existsById(foodId))
			throw new ResourceNotFoundException("Food Id not found");

		OrderModel orderModel = orderRepo.findById(orderId).orElse(null);
		FoodModel foodModel = foodRepo.findById(foodId).orElse(null);
		float orderPrice = orderModel.getOrderPrice();
		int quantity = orderModel.getQuantity();
		orderModel.setOrderPrice(orderPrice + foodModel.getFoodPrice());
		orderModel.getFoodModel().add(foodModel);
		orderModel.setQuantity(quantity + 1);
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

	// TODO

	public void showOrdersById(Integer restId) throws ResourceNotFoundException {

//		return new ResponseEntity<List<OrderModel>>( , HttpStatus.ACCEPTED);
	}

}
