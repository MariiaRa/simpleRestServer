package ua.com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.entity.ShippingAddress;
import ua.com.repository.ShippingAddressRepository;
import ua.com.service.ShippingAddressService;

@Service
public class ShippingAddressServiceImpl implements ShippingAddressService{
	@Autowired
	ShippingAddressRepository repository;

	@Override
	public ShippingAddress save(ShippingAddress shipAddress) {
				return repository.save(shipAddress);
	}

	@Override
	public List<ShippingAddress> findAll() {
				return repository.findAll();
	}

	@Override
	public ShippingAddress findOne(Integer id) {
				return repository.findOne(id);
	}

	@Override
	public boolean delete(Integer id) {
		repository.delete(id);
		return true;
	}

}
