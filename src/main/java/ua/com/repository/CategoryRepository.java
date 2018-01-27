package ua.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.com.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

	Category findByName(String name);
	
}