package com.train.ecommerce.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.train.ecommerce.model.Orders;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {

}
