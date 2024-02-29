package com.train.ecommerce.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String description;

	private Double price;

	@ManyToMany(mappedBy = "product")
	@JsonIgnore
	private Set<Cart> cart = new HashSet<Cart>();

	@ManyToMany(mappedBy = "products")
	@JsonIgnore
	private Set<Orders> orders = new HashSet<Orders>();

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public Product(Long id, String name, String description, Double price, Set<Cart> cart, Set<Orders> orders) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.cart = cart;
		this.orders = orders;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Set<Cart> getCart() {
		return cart;
	}

	public void setCart(HashSet<Cart> cart) {
		this.cart = cart;
	}

	public Set<Orders> getOrders() {
		return orders;
	}

	public void setOrders(HashSet<Orders> orders) {
		this.orders = orders;
	}

}
