package com.train.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.train.ecommerce.model.Cart;
import com.train.ecommerce.model.Product;
import com.train.ecommerce.repository.CartRepository;
import com.train.ecommerce.repository.ProductRepository;
import com.train.ecommerce.repository.UserRepository;
import com.train.ecommerce.response.MessageResponse;
import com.train.ecommerce.model.User;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private UserRepository userRepository;
	

	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public ResponseEntity<MessageResponse> getAllCarts() {
		List<Cart> result = cartRepository.findAll();
		if (result.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("Cart not found  ", null));
		} else {
			return ResponseEntity.ok(new MessageResponse("Successfully Fetched cart", result));
		}
	}

	@Override
	@Transactional
	public ResponseEntity<MessageResponse> createCart(Cart cartRequest) {
		try {
			Cart result = cartRepository.save(cartRequest);
			return ResponseEntity.ok(new MessageResponse("Cart Added", result));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new MessageResponse("No cart Added", null));
		}

	}

	@Override
	@Transactional
	public ResponseEntity<MessageResponse> addProductToCart(Long userId, Long productId) {
		
		User user = userRepository.findById(userId).orElse(null);
		
        Product product = productRepository.findById(productId).orElse(null);
        
        if (user == null || product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("User or product not found",null));   		
        }
        Cart cart = user.getCart();
        cart.getProduct().add(product);
        Double Total= cart.getTotal();
        cart.setTotal(Total+product.getPrice());
        cartRepository.save(cart);

        return ResponseEntity.ok(new MessageResponse("Successfully Fetched record", cart.getProduct()));
	}

	@Override
	public void clearCart(Cart cart) {
		 cart.getProduct().clear();
		    
		    // Update the total amount in the cart (if applicable)
		    cart.setTotal(0.0); // Assuming the total amount needs to be reset to 0
		    
		    // Save the updated cart to the database or perform any other necessary operations
		    cartRepository.save(cart);
		
	}



}
