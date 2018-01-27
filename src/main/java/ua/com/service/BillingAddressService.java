package ua.com.service;

import java.util.List;

import ua.com.entity.BillingAddress;

public interface BillingAddressService {

	BillingAddress save(BillingAddress billAddress);
	List<BillingAddress> findAll();
	BillingAddress findOne(Integer id);
	boolean delete(Integer id);
	
	
	
}
