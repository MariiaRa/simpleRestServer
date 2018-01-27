package ua.com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import ua.com.entity.Book;
import ua.com.entity.Category;
import ua.com.repository.CategoryRepository;
import ua.com.request.MyPageRequest;
import ua.com.response.BookResponse;
import ua.com.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
@Autowired
	CategoryRepository repository;
	
	public Category save(Category category) {
		return repository.save(category);
			}

	public List<Category> findAll() {
		return repository.findAll();
	}

	public Category findOne(Integer id) {
		return repository.findOne(id);
	}

	public boolean delete(Integer id) {
		repository.delete(id);
		 return true;
	}

   public Category findByName(String categoryName) {
		return repository.findByName(categoryName);
	}

@Override
public Page<Category> findAll(MyPageRequest page) {
	PageRequest pageRequest = new PageRequest(page.getNumberPage(),page.getSizePage());
	Page<Category> pageBooks =  repository.findAll(pageRequest);
		return pageBooks;
}
		
}
