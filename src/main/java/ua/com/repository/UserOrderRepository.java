package ua.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import ua.com.entity.User;
import ua.com.entity.UserOrder;


public interface UserOrderRepository extends JpaRepository <UserOrder, Integer>,JpaSpecificationExecutor<UserOrder>{

	
	
}
