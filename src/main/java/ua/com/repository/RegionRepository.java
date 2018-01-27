package ua.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.com.entity.Region;

public interface RegionRepository extends JpaRepository<Region, Integer> {

	
}
