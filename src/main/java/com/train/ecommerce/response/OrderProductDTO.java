package com.train.ecommerce.response;

public class OrderProductDTO {
	private String userName;
	private Long orderId;
	private Long productId;
	private String productName;
	private int count;

	public OrderProductDTO() {
		super();
	}

	public OrderProductDTO(String userName, Long orderId, Long productId, String productName, int count) {
		super();
		this.userName = userName;
		this.orderId = orderId;
		this.productId = productId;
		this.productName = productName;
		this.count = count;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void incrementCount() {
		this.count++;
	}

}
