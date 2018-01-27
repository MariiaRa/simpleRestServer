package ua.com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.entity.OrderDetails;
import ua.com.repository.OrderDetailsRepository;
import ua.com.service.OrderDetailsService;
@Service
public class OrderDetailsServiceImpl implements OrderDetailsService{

	@Autowired
	OrderDetailsRepository repository;
	
	
	@Override
	public OrderDetails save(OrderDetails orderDetails) {
	return repository.save(orderDetails);
	}

	@Override
	public List<OrderDetails> findAll() {
		return repository.findAll();
	}

	@Override
	public OrderDetails findOne(Integer id) {
				return repository.findOne(id);
	}

	@Override
	public boolean delete(Integer id) {
		repository.delete(id);
		return true;
	}

}
