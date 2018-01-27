package ua.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.com.entity.Publisher;

public interface PublisherRepository extends JpaRepository<Publisher, Integer> {
	
Publisher findByName(String name);

}
