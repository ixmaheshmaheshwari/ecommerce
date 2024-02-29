package com.train.ecommerce.service;

import org.springframework.http.ResponseEntity;

import com.train.ecommerce.model.User;
import com.train.ecommerce.response.MessageResponse;



public interface UserService {



	public ResponseEntity<MessageResponse> createUser(User userRequest);

	public ResponseEntity<MessageResponse> getallUsers();

	public ResponseEntity<MessageResponse> updateUser(User userRequest, Long id);

	public ResponseEntity<MessageResponse> deleteUser(Long id);

}
