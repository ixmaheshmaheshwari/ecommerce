package com.train.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.train.ecommerce.model.Cart;
import com.train.ecommerce.model.User;
import com.train.ecommerce.repository.CartRepository;
import com.train.ecommerce.repository.UserRepository;
import com.train.ecommerce.response.MessageResponse;

@Service
public class UserServiceImpl implements UserService {

	@Autowired 
	private UserRepository userRepository;
	
	
	@Autowired
	private CartRepository cartRepository;

	@Override
	public ResponseEntity<MessageResponse> getallUsers() {
		
		List<User> result= userRepository.findAll();
		if (result.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("User not found  ", null));
        } else {
            return ResponseEntity.ok(new MessageResponse("Successfully Fetched record", result));
        }
	}

	

	@Override
	public ResponseEntity<MessageResponse> createUser(User userRequest) {
		
		 try {
			 // Check if the user already has a cart
	            if (userRequest.getCart() == null) {
	                // If not, create a new Cart for the user
	                Cart cart = new Cart();
	                cart.setUser(userRequest);
	                cart.setTotal(0.0);
	                cartRepository.save(cart);

	                userRequest.setCart(cart);
	                
	               


	                User createdUser = userRepository.save(userRequest);
  
	            return ResponseEntity.ok(new MessageResponse("User Added",createdUser));
	            }
	            else {
	            	return ResponseEntity.badRequest().body(new MessageResponse("User already has a Cart",null));
	            }
	        } catch (Exception e) {
	        	return ResponseEntity.badRequest().body(new MessageResponse("No user Added",e));
	        }
		
	}



	@Override
	@Transactional
	public ResponseEntity<MessageResponse> updateUser(User userRequest, Long id) {
		if (userRequest == null) {
            return ResponseEntity.ofNullable(new MessageResponse("User data cannot be null", null));
            		
        }

        
        if (id == null || id <= 0) {
        	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("Id cannot be null: " + id,null));  
        }
	
	Optional<User>existingUserOptional = userRepository.findById(id);
	if (existingUserOptional.isEmpty()) {
    	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("Task not found with id: " + id,null));        
    	}
	User existingUser=existingUserOptional.get();
	
	if(userRequest.getEmail()!= null) {
		existingUser.setEmail(userRequest.getEmail());
	}
	if(userRequest.getPassword()!= null) {
		existingUser.setPassword(userRequest.getPassword());
	}
	if(userRequest.getUsername()!= null) {
		existingUser.setUsername(userRequest.getUsername());
	}
	try {
        User updatedUser = userRepository.save(existingUser);
        return ResponseEntity.ok(new MessageResponse("User updated successfully", updatedUser));
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new MessageResponse("Failed to update user", null));
    }
	}



	@Override
	public ResponseEntity<MessageResponse> deleteUser(Long id) {
		
		boolean isDeleted = userRepository.existsById(id);
		if (isDeleted) {
			Optional<User> optionalUser= userRepository.findById(id);
			User user = optionalUser.get();
			userRepository.delete(user);
			 return ResponseEntity.ok(new MessageResponse("User deleted successfully",user));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("User not found with id: " + id,null));
        }
		}
	

	

	

}
