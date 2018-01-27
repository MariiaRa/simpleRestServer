package ua.com.service;


import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;

import ua.com.entity.Book;
import ua.com.entity.Category;
import ua.com.request.CategorySearchPageRequest;
import ua.com.request.MyPageRequest;
import ua.com.request.SearchingRequest;
import ua.com.response.BookResponse;

public interface BookService {
//void save(Book book, int idCategory, int idPublisher, int idPrice, int idAuthor);
Book save(Book book) throws IOException;
List<Book> findAll();
Book findOne(Integer id);
boolean delete(Integer id);
Page<BookResponse> findAll(MyPageRequest page);
List<Book> findAll(SearchingRequest searchingRequest);
Page<BookResponse> findAllReturnPage(CategorySearchPageRequest request);
Page<BookResponse> findAllandSortByTitleAsc(MyPageRequest page);
Page<BookResponse> findAllandSortByTitleDesc(MyPageRequest page);

Page<BookResponse> findAllandSortByPriceAsc(MyPageRequest page);
Page<BookResponse> findAllandSortByPriceDesc(MyPageRequest page);


Page<BookResponse> findAllByCategoryAndSort(Category category, CategorySearchPageRequest searchingRequest);
Page<BookResponse> findAllandSortByDatePubAsc(MyPageRequest myPageRequest);
Page<BookResponse> findAllandSortByDatePubDesc(MyPageRequest myPageRequest);
Page<BookResponse> findAllByCategory(Category category, CategorySearchPageRequest searchingRequest);
Page<BookResponse> findAllByCategoryAndSortPrice(Category category, CategorySearchPageRequest searchingRequest);

Page<BookResponse> findAllByCategoryAndSortDate(Category category, CategorySearchPageRequest searchingRequest);
Page<BookResponse> findAllandSortByTitleAsc(CategorySearchPageRequest request);
Page<BookResponse> findAllandSortByPriceAsc(CategorySearchPageRequest request);
Page<BookResponse> findAllandSortByDatePubDesc(CategorySearchPageRequest request);
}
