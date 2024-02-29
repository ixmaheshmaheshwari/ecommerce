package com.train.ecommerce.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Orders")
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	 
	private String orderNumber;

	private Date orderDate;

	@ManyToMany
	@JoinTable(name = "product_orders", joinColumns = @JoinColumn(name = "order_id"), inverseJoinColumns = @JoinColumn(name = "product_id"))
	private Set<Product> products = new HashSet<Product>();

	@ManyToOne
	@JoinColumn(name = "cart_id")
	
	private Cart cartOrderRef;

	public Orders() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Orders(Long id, String orderNumber, Date orderDate, HashSet<Product> products, Cart cartOrderRef) {
		super();
		this.id = id;
		this.orderNumber = orderNumber;
		this.orderDate = orderDate;
		this.products = products;
		this.cartOrderRef = cartOrderRef;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	public Cart getCartOrderRef() {
		return cartOrderRef;
	}

	public void setCartOrderRef(Cart cartOrderRef) {
		this.cartOrderRef = cartOrderRef;
	}

}
