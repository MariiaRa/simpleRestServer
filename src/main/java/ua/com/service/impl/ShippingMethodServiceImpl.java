package ua.com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.entity.ShippingMethod;
import ua.com.repository.ShippingMethodRepository;
import ua.com.service.ShippingMethodService;
@Service
public class ShippingMethodServiceImpl implements ShippingMethodService {
@Autowired
	ShippingMethodRepository repository;
	
	
	@Override
	public ShippingMethod save(ShippingMethod shippingMethod) {
		return repository.save(shippingMethod);
	}

	@Override
	public List<ShippingMethod> findAll() {
			return repository.findAll();
	}

	@Override
	public ShippingMethod findOne(Integer id) {
		return repository.findOne(id);
	}

	@Override
	public boolean delete(Integer id) {
		repository.delete(id);
		return true;
	}

}
