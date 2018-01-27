package ua.com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import ua.com.entity.Book;
import ua.com.entity.Category;

public interface BookRepository extends JpaRepository <Book, Integer>, JpaSpecificationExecutor<Book> {

	List<Book> findAllByCategory(Category category);

   
    
	
}