package ua.com.service;

import java.util.List;

import ua.com.entity.ShippingAddress;

public interface ShippingAddressService {
	ShippingAddress save(ShippingAddress shippingAddress);
	List<ShippingAddress> findAll();
	ShippingAddress findOne(Integer id);
	boolean delete(Integer id);
}
