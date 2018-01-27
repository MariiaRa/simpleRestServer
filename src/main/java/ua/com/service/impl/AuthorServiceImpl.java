package ua.com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import ua.com.entity.Author;
import ua.com.entity.Book;
import ua.com.entity.Category;
import ua.com.repository.AuthorRepository;
import ua.com.request.MyPageRequest;
import ua.com.service.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService{
@Autowired
	AuthorRepository repository;
	
	
	public Author save(Author author) {
		 return repository.save(author);		
	}

	public List<Author> findAll() {
		return repository.findAll();
	}

	public Author findOne(Integer id) {
		return repository.findOne(id);
	}

	public boolean delete(Integer id) {
		repository.delete(id);
		return true;
		
	}

	public Author findByLastName(String name) {
		return repository.findByLastName(name);
	}

	@Override
	public Page<Author> findAll(MyPageRequest myPageRequest) {
		PageRequest pageRequest = new PageRequest(myPageRequest.getNumberPage(),myPageRequest.getSizePage());
		Page<Author> pageBooks =  repository.findAll(pageRequest);
			return pageBooks;
	}


	
	
	
}
