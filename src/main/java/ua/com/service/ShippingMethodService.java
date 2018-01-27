package ua.com.service;

import java.util.List;

import ua.com.entity.ShippingMethod;

public interface ShippingMethodService {

	ShippingMethod save(ShippingMethod shippingMethod);
	List<ShippingMethod> findAll();
	ShippingMethod findOne(Integer id);
	boolean delete(Integer id);
	
	
}
