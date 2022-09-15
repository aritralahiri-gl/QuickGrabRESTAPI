package com.quickgrab.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.quickgrab.dto.RestaurantOrder;
import com.quickgrab.exception.ResourceNotFoundException;
import com.quickgrab.model.FoodModel;
import com.quickgrab.model.OrderModel;
import com.quickgrab.repository.CustomerRepo;
import com.quickgrab.repository.FoodRepo;
import com.quickgrab.repository.OrderRepo;
import com.quickgrab.repository.RestaurantRepo;

@Service
public class OrderService {

	@Autowired
	private OrderRepo orderRepo;
	@Autowired
	private FoodRepo foodRepo;
	@Autowired
	private CustomerRepo customerRepo;
	@Autowired
	private RestaurantRepo restaurantRepo;

	public ResponseEntity<OrderModel> createOrder(Integer custId, OrderModel orderModel)
			throws ResourceNotFoundException {

		if (!customerRepo.existsById(custId))
			throw new ResourceNotFoundException("Customer Id not found");

		orderModel.setCustId(custId);

		return new ResponseEntity<OrderModel>(orderRepo.save(orderModel), HttpStatus.CREATED);
	}

	public ResponseEntity<OrderModel> addFoodToOrder(Integer foodId, Integer orderId) throws ResourceNotFoundException {

		OrderModel orderModel = orderRepo.findById(orderId)
				.orElseThrow(() -> new ResourceNotFoundException("Order Id Not Found"));
		FoodModel foodModel = foodRepo.findById(foodId)
				.orElseThrow(() -> new ResourceNotFoundException("Food Id Not Found"));
		float orderPrice = orderModel.getOrderPrice();
		int quantity = orderModel.getQuantity();
		orderModel.setOrderPrice(orderPrice + foodModel.getFoodPrice());
		orderModel.getFoodModel().add(foodModel);
		orderModel.setQuantity(quantity + 1);
		return new ResponseEntity<OrderModel>(orderRepo.save(orderModel), HttpStatus.CREATED);
	}

	public ResponseEntity<List<OrderModel>> showAllOrders() {

		List<OrderModel> orderModel = orderRepo.findAll();

		return new ResponseEntity<List<OrderModel>>(orderModel, HttpStatus.OK);
	}

	public ResponseEntity<?> deleteOrder(Integer orderId) throws ResourceNotFoundException {

		if (!orderRepo.existsById(orderId)) {

			throw new ResourceNotFoundException("Order Id not found");

		}
		orderRepo.deleteById(orderId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	public List<RestaurantOrder> showOrdersById(Integer restId) throws ResourceNotFoundException {

		if (!restaurantRepo.existsById(restId)) {

			throw new ResourceNotFoundException("Restaurant Id not found");

		}

		List<RestaurantOrder> list = new ArrayList<>();
		list.addAll(orderRepo.getOrderRestaurantInfo(restId));

		return list;

	}

}
