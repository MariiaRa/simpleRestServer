package ua.com.service;


import java.util.List;

import org.springframework.data.domain.Page;

import ua.com.entity.Category;
import ua.com.request.MyPageRequest;
import ua.com.response.BookResponse;

public interface CategoryService {
	Category save(Category category);
	List<Category> findAll();
	Category findOne(Integer id);
	boolean delete(Integer id);
	Category findByName(String categoryName);
	Page<Category> findAll(MyPageRequest page);
	
	
}
