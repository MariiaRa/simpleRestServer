package ua.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.com.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Integer> {

	Author findByLastName(String name);
	
	
}
