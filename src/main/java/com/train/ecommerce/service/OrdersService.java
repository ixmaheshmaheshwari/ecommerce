package com.train.ecommerce.service;

import org.springframework.http.ResponseEntity;

import com.train.ecommerce.response.MessageResponse;


public interface OrdersService {

	public ResponseEntity<MessageResponse> orderProducts(Long userId);

	public ResponseEntity<MessageResponse> getOrderedProductsByUser(Long userId);

}
