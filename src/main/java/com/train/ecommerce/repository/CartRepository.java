package com.train.ecommerce.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.train.ecommerce.model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

	
}
