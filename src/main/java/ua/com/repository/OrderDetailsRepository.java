package ua.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.com.entity.OrderDetails;


public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Integer> {

	
		
}
