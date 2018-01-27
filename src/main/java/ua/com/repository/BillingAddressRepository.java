package ua.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.com.entity.BillingAddress;



public interface BillingAddressRepository extends JpaRepository <BillingAddress, Integer> {

	
}
