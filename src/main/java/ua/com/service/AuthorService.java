package ua.com.service;

import java.util.List;

import org.springframework.data.domain.Page;

import ua.com.entity.Author;
import ua.com.entity.Book;
import ua.com.request.MyPageRequest;

public interface AuthorService {
	Author save(Author author);
	List<Author> findAll();
	Author findOne(Integer id);
	boolean delete(Integer id);
	Author findByLastName(String name);
//	List<Book> searchBooksByAuthor(String name);
	Page<Author> findAll(MyPageRequest myPageRequest);
}
