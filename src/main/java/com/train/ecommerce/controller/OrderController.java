package com.train.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.train.ecommerce.response.MessageResponse;
//import com.train.ecommerce.response.OrderProductDTO;
import com.train.ecommerce.service.OrdersService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

	@Autowired
	private OrdersService orderService;

	@PostMapping("/order")
	public ResponseEntity<MessageResponse> orderProducts(@RequestParam Long userId) {
		return orderService.orderProducts(userId);
	}

	@GetMapping("/user/{userId}/products")
	public ResponseEntity<MessageResponse> getOrderedProductsByUser(@PathVariable Long userId) {
		return orderService.getOrderedProductsByUser(userId);
	}
}
