package com.train.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.train.ecommerce.model.User;
import com.train.ecommerce.response.MessageResponse;
import com.train.ecommerce.service.UserService;





@RestController
@RequestMapping("/api/user")

public class UserController {

	@Autowired
	private UserService userService;
	
	  @GetMapping("/")
	  public ResponseEntity<MessageResponse> getallUsers(){
		  return userService.getallUsers();
	  }
	  
	  @PostMapping("/")
	    public ResponseEntity< MessageResponse> createUser(@RequestBody User userRequest) {
	    	return userService.createUser(userRequest);
	    }
	  
	  @PutMapping("/{id}")
	  public ResponseEntity<MessageResponse> updateUser(@RequestBody User userRequest,@PathVariable Long id){
		  return userService.updateUser(userRequest,id);
	  }
	  
	  @DeleteMapping("/{id}")
	  public ResponseEntity<MessageResponse> deleteUser(@PathVariable Long id){
		  return userService.deleteUser(id);
	  }
}
