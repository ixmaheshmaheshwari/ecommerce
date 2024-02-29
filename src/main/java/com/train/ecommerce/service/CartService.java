package com.train.ecommerce.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.train.ecommerce.model.Cart;
import com.train.ecommerce.response.MessageResponse;
@Service
public interface CartService {

	public ResponseEntity<MessageResponse> getAllCarts();

	public ResponseEntity<MessageResponse> createCart(Cart cartRequest);

	public ResponseEntity<MessageResponse> addProductToCart(Long userId, Long productId);

	public void clearCart(Cart cart);



	 
}
