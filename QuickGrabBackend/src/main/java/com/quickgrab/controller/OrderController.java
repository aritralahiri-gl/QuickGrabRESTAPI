package com.quickgrab.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quickgrab.model.OrderModel;
import com.quickgrab.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@PostMapping("/{cId}")
	public ResponseEntity<OrderModel> createOrder(@PathVariable Integer cId, @RequestBody OrderModel orderModel) {

		return orderService.createOrder(cId, orderModel);

	}

	@PostMapping("/{fId}/{oId}")
	public ResponseEntity<OrderModel> addFoodToOrder(@PathVariable Integer fId, @PathVariable Integer oId) {

		return orderService.addFoodToOrder(fId, oId);

	}

}
