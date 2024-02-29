package com.train.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//import com.train.ecommerce.model.Cart;
import com.train.ecommerce.response.MessageResponse;
import com.train.ecommerce.service.CartService;

@RestController
@RequestMapping("/api/cart")

public class CartController {
	@Autowired
	private CartService cartService;

	@GetMapping("/getall")
	public ResponseEntity<MessageResponse> getAllCarts() {
		return cartService.getAllCarts();
	}

	@PostMapping("/addproduct")
	public ResponseEntity<MessageResponse> addProductToCart(@RequestParam Long userId, @RequestParam Long productId) {
		return cartService.addProductToCart(userId, productId);
	}

}
