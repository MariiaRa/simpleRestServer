package ua.com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.entity.BillingAddress;
import ua.com.repository.BillingAddressRepository;
import ua.com.service.BillingAddressService;

@Service
public class BillingAddressServiceImpl implements BillingAddressService {
	@Autowired
	BillingAddressRepository repository;

	@Override
	public BillingAddress save(BillingAddress billAddress) {
				return repository.save(billAddress);
	}

	@Override
	public List<BillingAddress> findAll() {
				return repository.findAll();
	}

	@Override
	public BillingAddress findOne(Integer id) {
				return repository.findOne(id);
	}

	@Override
	public boolean delete(Integer id) {
		repository.delete(id);
		return true;
	}

}
