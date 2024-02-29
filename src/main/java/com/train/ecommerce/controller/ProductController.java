package com.train.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.train.ecommerce.model.Product;
import com.train.ecommerce.response.MessageResponse;
import com.train.ecommerce.service.ProductService;

@RestController
@RequestMapping("/api/product")

public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping("/")
	public ResponseEntity<MessageResponse> getAllProducts(){
		return  productService.getAllProducts();
	}

	@PostMapping("/create")
	public ResponseEntity<MessageResponse> createProduct(@RequestBody Product productRequest){
		return  productService.createProduct(productRequest);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<MessageResponse> updateProduct(@PathVariable Long id,@RequestBody Product productRequest){
		return productService.updateProduct(id,productRequest);
	}
	
//	@DeleteMapping("/delete/{id}")
//	public ResponseEntity<MessageResponse> deleteProduct(@PathVariable Long id){
//		return productService.deleteProduct(id);
//	}

	
}
