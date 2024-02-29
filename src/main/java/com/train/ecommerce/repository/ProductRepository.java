package com.train.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.train.ecommerce.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
