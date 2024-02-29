package com.train.ecommerce.model;


import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Cart")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Double total;
	

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    
	private User user;
    
    @ManyToMany
    @JoinTable(
      name = "cart_product", 
      joinColumns = @JoinColumn(name = "cart_id"), 
      inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<Product> product = new HashSet<Product>();

    @OneToMany(mappedBy = "cartOrderRef", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
@JsonIgnore
    private Set<Orders> orders = new HashSet<Orders>();

		public Cart() {
			super();
		// TODO Auto-generated constructor stub
	}

		public Cart(Long id, Double total, User user, Set<Product> product, Set<Orders> orders) {
			super();
			this.id = id;
			this.total = total;
			this.user = user;
			this.product = product;
			this.orders = orders;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Double getTotal() {
			return total;
		}

		public void setTotal(Double total) {
			this.total = total;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public Set<Product> getProduct() {
			return product;
		}

		public void setProduct(HashSet<Product> product) {
			this.product = product;
		}

		public Set<Orders> getOrders() {
			return orders;
		}

		public void setOrders(Set<Orders> orders) {
			this.orders = orders;
		}

	
}
