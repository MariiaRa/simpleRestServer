package ua.com.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ua.com.entity.Author;
import ua.com.entity.Book;
import ua.com.entity.Category;
import ua.com.entity.Publisher;
import ua.com.repository.BookRepository;
import ua.com.request.BookUpdateRequest;
import ua.com.request.CategorySearchPageRequest;
import ua.com.request.CategoryUpdateRequest;
import ua.com.request.DeleteUserRequest;
import ua.com.request.MyPageRequest;
import ua.com.request.SearchingRequest;
import ua.com.response.BookResponse;
import ua.com.service.AuthorService;
import ua.com.service.BookService;
import ua.com.service.CategoryService;
import ua.com.service.PublisherService;

@CrossOrigin
@RestController
@RequestMapping("/book")
public class BookPropertiesController {
	@Autowired
	CategoryService servicec;
	
	@Autowired
	AuthorService servicea;
	
	@Autowired
	PublisherService servicep;
	
	@Autowired
	BookService serviceb;
	
	@Autowired
	private BookRepository repository;

	//GET
	
	@GetMapping("/categories")
	public List<Category> categories(){
		return servicec.findAll();
		}
	
	@GetMapping("/publishers")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<Publisher> publishers(){
		return servicep.findAll(); в
		}
	
	@GetMapping("/authors")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<Author> authors(){
		return servicea.findAll();
		}
	
	@GetMapping
	public List<Book> books(){
		return serviceb.findAll();
		}
		
	//PUT
	
	@PutMapping("/category/add")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Category addCategory(@RequestBody Category category) {
		return servicec.save(category);
	}
	
	@PutMapping("/publisher/add")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Publisher addPublisher(@RequestBody Publisher publisher) {
		return servicep.save(publisher);
	}
	
	@PutMapping("/author/add")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Author addAuthor(@RequestBody Author author) {
		return servicea.save(author);
	}
	
	@PutMapping("/add")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Book addBook(@RequestBody Book book) throws IOException {
		return serviceb.save(book);
	}
	
	//DELETE
	
	@DeleteMapping("/category/delete")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public boolean deleteCategory(@RequestBody DeleteUserRequest request){
		return servicec.delete(request.getId());
	}
	
	@DeleteMapping("/publisher/delete")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public boolean deletePublisher(@RequestBody DeleteUserRequest request){
		return servicep.delete(request.getId());
	}
	
	@DeleteMapping("/author/delete")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public boolean deleteAuthor(@RequestBody DeleteUserRequest request){
		return servicea.delete(request.getId());
	}
	
	@DeleteMapping("/delete")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public boolean deleteBook(@RequestBody DeleteUserRequest request){
		return serviceb.delete(request.getId());
	}
	
	//POST
			
	@PostMapping("/category/update")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public boolean updateCategory(@RequestBody CategoryUpdateRequest request){
		Category category = servicec.findOne(request.getId());
		category.setName(request.getName());
		servicec.save(category);
		return true;
	}
	
	@PostMapping("/page")
	public Page<BookResponse> bookPages(@RequestBody MyPageRequest myPageRequest){
		return serviceb.findAll(myPageRequest);
	}
		
	@PostMapping("/page/authors")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Page<Author> authorPages(@RequestBody MyPageRequest myPageRequest){
		return servicea.findAll(myPageRequest);
	}
	
	@PostMapping("/page/publishers")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Page<Publisher> publisherPages(@RequestBody MyPageRequest myPageRequest){
		return servicep.findAll(myPageRequest);
	}
	
	@PostMapping("/page/sort/by/title/asc")
	public Page<BookResponse> sortByTitleAsc(@RequestBody MyPageRequest myPageRequest){
		return serviceb.findAllandSortByTitleAsc(myPageRequest);
	}
	

	
	@PostMapping("/page/sort/by/price/asc")
	public Page<BookResponse> sortByPriceAsc(@RequestBody MyPageRequest myPageRequest){
		return serviceb.findAllandSortByPriceAsc(myPageRequest);
	}
	

	
	@PostMapping("/page/sort/by/datepub/asc")
	public Page<BookResponse> sortByDatePubAsc(@RequestBody MyPageRequest myPageRequest){
		return serviceb.findAllandSortByDatePubAsc(myPageRequest);
	}

	
	@PostMapping("/update")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public boolean bookUpdate(@RequestBody  BookUpdateRequest request) throws IOException{
	Book book = serviceb.findOne(request.getId());
	book.setPriceEbook(request.getPriceEbook());
	book.setPriceprintedbook(request.getPriceprintedbook());
	if(request.getBookCoverImage()!=null) {
	book.setBookCoverImage(request.getBookCoverImage());
	}
	book.setBookBriefDescription(request.getBookBriefDescription());
	book.setBookFullDescription(request.getBookFullDescription());
	book.setStock(request.getStock());
	book.setStatus(request.isStatus());
	book.setFeature(request.getFeature());
	repository.save(book);
	return true;
	}
	@PostMapping("/search")
	public List<Book> books(@RequestBody SearchingRequest searchingRequest){
		return serviceb.findAll(searchingRequest);
	}
	
	@PostMapping("/search/page")
	public Page<BookResponse> booksInSearch(@RequestBody CategorySearchPageRequest searchingRequest){
		return serviceb.findAllReturnPage(searchingRequest);
	}
	
	@PostMapping("/search/sort/asc")
	public Page<BookResponse> booksInSearchSortedAsc(@RequestBody CategorySearchPageRequest searchingRequest){
		return serviceb.findAllandSortByTitleAsc(searchingRequest); 
	}
	
	@PostMapping("/search/sort/price/asc")
	public Page<BookResponse> booksInSearchSortedPriceAsc(@RequestBody CategorySearchPageRequest searchingRequest){
		return serviceb.findAllandSortByPriceAsc(searchingRequest); 
	}
	
	@PostMapping("/search/sort/date/desc")
	public Page<BookResponse> booksInSearchSortedDateDesc(@RequestBody CategorySearchPageRequest searchingRequest){
		return serviceb.findAllandSortByDatePubDesc(searchingRequest); 
	}
		
	@PostMapping("/findByCategory")
	public Page<BookResponse> booksInCategory(@RequestBody CategorySearchPageRequest searchingRequest){
		Category category = servicec.findByName(searchingRequest.getSearchTerm());
		return serviceb.findAllByCategory(category, searchingRequest);
		}
	
	@PostMapping("/findByCategory/sort/asc")
	public Page<BookResponse> booksInCategorySortedAsc(@RequestBody CategorySearchPageRequest searchingRequest){
		Category category = servicec.findByName(searchingRequest.getSearchTerm());
		return serviceb.findAllByCategoryAndSort(category, searchingRequest);
		}
	
	@PostMapping("/findByCategory/sort/price/asc")
	public Page<BookResponse> booksInCategoryPriceSortedAsc(@RequestBody CategorySearchPageRequest searchingRequest){
		Category category = servicec.findByName(searchingRequest.getSearchTerm());
		return serviceb.findAllByCategoryAndSortPrice(category, searchingRequest);
		}
	
	@PostMapping("/findByCategory/sort/date/desc")
	public Page<BookResponse> booksInCategoryDateSortedDesc(@RequestBody CategorySearchPageRequest searchingRequest){
		Category category = servicec.findByName(searchingRequest.getSearchTerm());
		return serviceb.findAllByCategoryAndSortDate(category, searchingRequest);
		}
		
}
