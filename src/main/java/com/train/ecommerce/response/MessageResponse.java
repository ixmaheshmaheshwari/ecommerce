package com.train.ecommerce.response;

public class MessageResponse {
	private String message;
	private Object result;

	

	public MessageResponse(String message, Object result) {
		super();
		this.message = message;
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

}
