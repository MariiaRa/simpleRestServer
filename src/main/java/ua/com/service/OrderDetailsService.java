package ua.com.service;

import java.util.List;

import ua.com.entity.OrderDetails;

public interface OrderDetailsService {
	OrderDetails save(OrderDetails orderDetails);
	List<OrderDetails> findAll();
	OrderDetails findOne(Integer id);
	boolean delete(Integer id);
}
