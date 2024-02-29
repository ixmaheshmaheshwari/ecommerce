package com.train.ecommerce.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.train.ecommerce.model.Cart;
import com.train.ecommerce.model.Orders;
import com.train.ecommerce.model.Product;
import com.train.ecommerce.model.User;
import com.train.ecommerce.repository.OrdersRepository;
import com.train.ecommerce.repository.UserRepository;
import com.train.ecommerce.response.MessageResponse;
import com.train.ecommerce.response.OrderProductDTO;

@Service
public class OrdersServiceImpl implements OrdersService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OrdersRepository ordersRepository;

	@Autowired
	private CartService cartService;
	 @Autowired
	    private JdbcTemplate jdbcTemplate;

	@Override
	public ResponseEntity<MessageResponse> orderProducts(Long userId) {
		Optional<User> userOptional = userRepository.findById(userId);
		if (userOptional.isEmpty()) {
			return ResponseEntity.badRequest().body(new MessageResponse("User not found", null));
		}
		User user = userOptional.get();
		Cart cart = user.getCart();
		if (cart == null || cart.getProduct().isEmpty()) {
			return ResponseEntity.badRequest().body(new MessageResponse("Cart is empty", null));
		}
//        
		Orders order = createOrderFromCart(cart);

		// Clear the cart for the user
		cartService.clearCart(cart);
		return ResponseEntity.ok(new MessageResponse("Orders is added and cart is cleared", order));

	}

	public Long findLatestOrderNumber() {
        String sqlQuery = "SELECT MAX(CAST(SUBSTRING(o.orderNumber, 5) AS bigint)) FROM Orders o";
        return jdbcTemplate.queryForObject(sqlQuery, Long.class);
    }

	private String generateOrderNumber() {
        Long latestOrderNumber = findLatestOrderNumber();
        if (latestOrderNumber == null) {
            return "ord-1";
        } else {
            return "ord-" + (latestOrderNumber + 1);
        }
    }
	private Orders createOrderFromCart(Cart cart) {
		Orders order = new Orders();

		Set<Product> productsInCart = cart.getProduct();
		order.setOrderDate(new Date()); // Set the current date as the order date
		order.setOrderNumber(generateOrderNumber()); // Generate a unique order number
		Set<Product> orderedProducts = new HashSet<>();

		// Iterate over products in the cart and add them to the order's product set
		for (Product product : productsInCart) {
			orderedProducts.add(product);
		}

		order.setProducts(orderedProducts); // Set the products in the order

		// Associate the order with the cart
		order.setCartOrderRef(cart);

		// Save the order to the database or perform any other necessary operations
		ordersRepository.save(order);

		return order;
	}

	@Override
	public ResponseEntity<MessageResponse> getOrderedProductsByUser(Long userId) {
		Optional<User> userOptional = userRepository.findById(userId);
		if (userOptional.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new MessageResponse("User not found with ID: " + userId, null));
		}

		// Retrieve orders for the user
		User user = userOptional.get();
		Cart cart = user.getCart();
		Set<Orders> orders = cart.getOrders();
		List<OrderProductDTO> orderedProducts = new ArrayList<>();
		for (Orders order : orders) {
			for (Product product : order.getProducts()) {
				// Check if the product exists in the DTO list
				Optional<OrderProductDTO> existingProduct = orderedProducts.stream()
						.filter(dto -> dto.getProductId().equals(product.getId())).findFirst();

				// If product exists, increment the count, else add a new DTO
				if (existingProduct.isPresent()) {
					existingProduct.get().incrementCount();
				} else {
					orderedProducts.add(new OrderProductDTO(order.getCartOrderRef().getUser().getUsername(),
							order.getId(), product.getId(), product.getName(), 1));
				}
			}
		}

		// Return the ordered products along with a success message
		return ResponseEntity.ok(new MessageResponse("Ordered products are retrieved successfully", orderedProducts));
	}

}
