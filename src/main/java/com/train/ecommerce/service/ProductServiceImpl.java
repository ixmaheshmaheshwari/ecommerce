package com.train.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.train.ecommerce.model.Product;
import com.train.ecommerce.repository.ProductRepository;
import com.train.ecommerce.response.MessageResponse;

import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

//	@Autowired
//	private CartService cartService;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public ResponseEntity<MessageResponse> getAllProducts() {

		List<Product> result = productRepository.findAll();
		if (result.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("Product not found  ", null));
		} else {
			return ResponseEntity.ok(new MessageResponse("Successfully Fetched record", result));
		}
	}

	@Override
	@Transactional
	public ResponseEntity<MessageResponse> createProduct(Product productRequest) {
		try {
			Product addedProduct = productRepository.save(productRequest);
			return ResponseEntity.ok(new MessageResponse("Product added successfully", addedProduct));
		} catch (Exception e) {
			// Log the exception for debugging purposes
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new MessageResponse("Failed to add product", null));
		}
	}

	@Override
	public ResponseEntity<MessageResponse> updateProduct(Long id, Product productRequest) {
		System.out.println(id);
		try {
			Optional<Product> optionalProduct = productRepository.findById(id);
			if (optionalProduct.isPresent()) {
				boolean isInCart = existsByProductId(id);
				if (isInCart) {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST)
							.body(new MessageResponse("The product is already added to a cart", null));
				}
				Product existingProduct= optionalProduct.get();
				
				existingProduct.setName(productRequest.getName());
				existingProduct.setPrice(productRequest.getPrice());
				existingProduct.setDescription(productRequest.getDescription());

				productRepository.save(existingProduct);
				return ResponseEntity.ok(new MessageResponse("Product updated successfully", existingProduct));
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(new MessageResponse("Product with ID " + id + " not found", null));
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new MessageResponse("Failed to update Product: " + e.getMessage(), null));
		}
	}

	public boolean existsByProductId(Long productId) {
		String sql = "SELECT COUNT(*) FROM cart_product WHERE product_id = ?";
		int count = jdbcTemplate.queryForObject(sql, Integer.class, productId);
		if (count > 0) {
			return true;
		} else {
			return false;
		}
	}

//	@Override
//	public ResponseEntity<MessageResponse> deleteProduct(Long id) {
//		try {
//			Optional<Product> optionalProduct = productRepository.findById(id);
//			if (optionalProduct.isPresent()) {
//				boolean isInCart = existsByProductId(id);
//				if (isInCart) {
//					return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//							.body(new MessageResponse("The product is already added to a cart", null));
//				}
//				Product existingProduct = optionalProduct.get();
//				productRepository.delete(existingProduct);
//				return ResponseEntity.ok(new MessageResponse("Product deleted successfully", existingProduct));
//
//			}
//
//			else {
//				return ResponseEntity.status(HttpStatus.NOT_FOUND)
//						.body(new MessageResponse("Product with ID " + id + " not found", null));
//			}
//		} catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//					.body(new MessageResponse("Failed to delete Product: " + e.getMessage(), null));
//		}
//
//	}

}
