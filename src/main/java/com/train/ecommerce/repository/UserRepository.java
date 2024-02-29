package com.train.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.train.ecommerce.model.User;


public interface UserRepository  extends JpaRepository<User,Long> {

}
