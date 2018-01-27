package ua.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.com.entity.ShippingAddress;

public interface ShippingAddressRepository extends JpaRepository <ShippingAddress, Integer> {

}
