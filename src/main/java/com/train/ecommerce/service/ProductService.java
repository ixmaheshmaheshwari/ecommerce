package com.train.ecommerce.service;

import org.springframework.http.ResponseEntity;

import com.train.ecommerce.model.Product;
import com.train.ecommerce.response.MessageResponse;

public interface ProductService {

	public ResponseEntity<MessageResponse> getAllProducts();

	public ResponseEntity<MessageResponse> createProduct(Product productRequest);

	public ResponseEntity<MessageResponse> updateProduct(Long id,Product productRequest);

	//public ResponseEntity<MessageResponse> deleteProduct(Long id);

}
