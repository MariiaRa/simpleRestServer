package ua.com.service.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import sun.misc.BASE64Decoder;
import ua.com.MyListComparator;
import ua.com.dto.Base64MultipartFile;
import ua.com.entity.Book;
import ua.com.entity.Category;
import ua.com.repository.BookRepository;
import ua.com.request.CategorySearchPageRequest;
import ua.com.request.MyPageRequest;
import ua.com.request.SearchingRequest;
import ua.com.response.BookResponse;
import ua.com.service.BookService;
import ua.com.specification.SearchBooksReturnPages;
import ua.com.specification.SearchingBook;
@Service
public class BookServiceImpl implements BookService {
	private final static String PATH = "C:\\Users\\user\\Desktop\\ajax (2)\\ajax\\image\\";

	
@Autowired
	private BookRepository repository;


private String sortOrder = "ASC";
private String sortBy = "title";
private String sortBy1 = "priceEbook";

//Sort sort = new Sort(Direction.fromString(sortOrder), sortBy);

public List<Book> findAll() {
	return repository.findAll();
}

public Book findOne(Integer id) {
	return repository.findOne(id);
}

public boolean delete(Integer id) {
	repository.delete(id);
	return true;
}

@Override
public Book save(Book book) throws IOException {
	byte[]fileContent;
	BASE64Decoder decoder = new BASE64Decoder();
	fileContent = decoder.decodeBuffer(book.getBookCoverImage().split(",")[1]);
	String expansion = book.getBookCoverImage().split(",")[0].split("/")[1].split(";")[0];
	book.setBookCoverImage(null);;
	book = repository.saveAndFlush(book);
	Base64MultipartFile multipartFile =new Base64MultipartFile(fileContent,book.getId()+"."+expansion);
	saveFile(multipartFile);
	book.setBookCoverImage(PATH+"id"+book.getId()+"."+expansion);
	
	return repository.save(book);	
}

private void saveFile(MultipartFile file) throws FileNotFoundException, IOException{
	File pathToFolder = new File(PATH);
	createFolder(pathToFolder);
	File newFile = new File(pathToFolder+"\\"+"id"+file.getOriginalFilename());
	writeFile(newFile,file);
	
}

private void createFolder(File path){
	if(!path.exists()){
		path.mkdirs();
	}
}

private void writeFile(File file ,MultipartFile multipartFile) throws FileNotFoundException, IOException{
	try(OutputStream fos = new FileOutputStream(file);BufferedOutputStream bos = new BufferedOutputStream(fos)){
		bos.write(multipartFile.getBytes(),0,multipartFile.getBytes().length);
		bos.flush();
	}catch (IOException e) {
		
	}
}


@Override
public Page<BookResponse> findAll(MyPageRequest page) {
	PageRequest pageRequest = new PageRequest(page.getNumberPage(),page.getSizePage());
	Page<Book> pageBooks =  repository.findAll(pageRequest);
	Page<BookResponse> pageBookResponse = pageBooks.map(this::convert);
	return pageBookResponse;
}

@Override
public Page<BookResponse> findAllandSortByTitleAsc(MyPageRequest page) {
	PageRequest pageRequest = new PageRequest(page.getNumberPage(),page.getSizePage(),Sort.Direction.ASC,"title");
	Page<Book> pageBooks =  repository.findAll(pageRequest);
	Page<BookResponse> pageBookResponse = pageBooks.map(this::convert);
	return pageBookResponse;
}



private BookResponse convert(Book book){
	return new BookResponse(book);
}

@Override
public List<Book> findAll(SearchingRequest searchingRequest) {
	SearchingBook searchingBook = new SearchingBook(searchingRequest);
	return repository.findAll(searchingBook);
}


@Override
public Page<BookResponse> findAllReturnPage(CategorySearchPageRequest request) {
	PageRequest pageRequest = new PageRequest(request.getNumberPage(),request.getSizePage());
	SearchBooksReturnPages searchingBook = new SearchBooksReturnPages(request);
	List<Book> booksPerSearch = repository.findAll(searchingBook);
	int max = (request.getSizePage()*(request.getNumberPage()+1)>booksPerSearch.size())? booksPerSearch.size(): request.getSizePage()*(request.getNumberPage()+1);
    Page<Book> pageBooks = new PageImpl<Book>(booksPerSearch.subList(request.getNumberPage()*request.getSizePage(), max), pageRequest, booksPerSearch.size()); 
    Page<BookResponse> pageBookResponse = pageBooks.map(this::convert);
		return pageBookResponse;
}



@Override
public Page<BookResponse> findAllandSortByTitleDesc(MyPageRequest page) {
	PageRequest pageRequest = new PageRequest(page.getNumberPage(),page.getSizePage(),Sort.Direction.DESC,"title");
	Page<Book> pageBooks =  repository.findAll(pageRequest);
	Page<BookResponse> pageBookResponse = pageBooks.map(this::convert);
	return pageBookResponse;
}

@Override
public Page<BookResponse> findAllandSortByPriceAsc(MyPageRequest page) {
	PageRequest pageRequest = new PageRequest(page.getNumberPage(),page.getSizePage(),Sort.Direction.ASC,"priceEbook");
	Page<Book> pageBooks =  repository.findAll(pageRequest);
	Page<BookResponse> pageBookResponse = pageBooks.map(this::convert);
	return pageBookResponse;
}

@Override
public Page<BookResponse> findAllandSortByPriceDesc(MyPageRequest page) {
	PageRequest pageRequest = new PageRequest(page.getNumberPage(),page.getSizePage(),Sort.Direction.DESC,"priceEbook");
	Page<Book> pageBooks =  repository.findAll(pageRequest);
	Page<BookResponse> pageBookResponse = pageBooks.map(this::convert);
	return pageBookResponse;
}

@Override
public Page<BookResponse> findAllByCategoryAndSort(Category category, CategorySearchPageRequest searchingRequest) {
	PageRequest pageRequest = new PageRequest(searchingRequest.getNumberPage(),searchingRequest.getSizePage());
List<Book> booksPerCategory = repository.findAllByCategory(category);
Collections.sort(booksPerCategory, new MyListComparator(sortBy, sortOrder));


int max = (searchingRequest.getSizePage()*(searchingRequest.getNumberPage()+1)>booksPerCategory.size())? booksPerCategory.size(): searchingRequest.getSizePage()*(searchingRequest.getNumberPage()+1);

Page<Book> pageBooks1 = new PageImpl<Book>(booksPerCategory.subList(searchingRequest.getNumberPage()*searchingRequest.getSizePage(), max), pageRequest, booksPerCategory.size()); 


//		Page<Book> pageBooks =  new PageImpl<Book>(booksPerCategory, pageRequest, booksPerCategory.size());
	Page<BookResponse> pageBookResponse = pageBooks1.map(this::convert);
	return pageBookResponse;
			}


@Override
public Page<BookResponse> findAllByCategoryAndSortPrice(Category category, CategorySearchPageRequest searchingRequest){
	
	PageRequest pageRequest = new PageRequest(searchingRequest.getNumberPage(),searchingRequest.getSizePage());
	List<Book> booksPerCategory = repository.findAllByCategory(category);
	Collections.sort(booksPerCategory, new MyListComparator(sortBy1, sortOrder));
	int max = (searchingRequest.getSizePage()*(searchingRequest.getNumberPage()+1)>booksPerCategory.size())? booksPerCategory.size(): searchingRequest.getSizePage()*(searchingRequest.getNumberPage()+1);
	Page<Book> pageBooks1 = new PageImpl<Book>(booksPerCategory.subList(searchingRequest.getNumberPage()*searchingRequest.getSizePage(), max), pageRequest, booksPerCategory.size()); 
	Page<BookResponse> pageBookResponse = pageBooks1.map(this::convert);
	return pageBookResponse;
	}


@Override
public Page<BookResponse> findAllByCategory(Category category, CategorySearchPageRequest searchingRequest) {
	PageRequest pageRequest = new PageRequest(searchingRequest.getNumberPage(),searchingRequest.getSizePage());
	List<Book> booksPerCategory = repository.findAllByCategory(category);
	int max = (searchingRequest.getSizePage()*(searchingRequest.getNumberPage()+1)>booksPerCategory.size())? booksPerCategory.size(): searchingRequest.getSizePage()*(searchingRequest.getNumberPage()+1);
    Page<Book> pageBooks1 = new PageImpl<Book>(booksPerCategory.subList(searchingRequest.getNumberPage()*searchingRequest.getSizePage(), max), pageRequest, booksPerCategory.size()); 
    Page<BookResponse> pageBookResponse = pageBooks1.map(this::convert);
		return pageBookResponse;
}


@Override
public Page<BookResponse> findAllandSortByDatePubAsc(MyPageRequest page) {
	PageRequest pageRequest = new PageRequest(page.getNumberPage(),page.getSizePage(),Sort.Direction.ASC,"published");
	Page<Book> pageBooks =  repository.findAll(pageRequest);
	Page<BookResponse> pageBookResponse = pageBooks.map(this::convert);
	return pageBookResponse;
}

@Override
public Page<BookResponse> findAllandSortByDatePubDesc(MyPageRequest page) {
	PageRequest pageRequest = new PageRequest(page.getNumberPage(),page.getSizePage(),Sort.Direction.DESC,"published");
	Page<Book> pageBooks =  repository.findAll(pageRequest);
	Page<BookResponse> pageBookResponse = pageBooks.map(this::convert);
	return pageBookResponse;
}

@Override
public Page<BookResponse> findAllByCategoryAndSortDate(Category category, CategorySearchPageRequest searchingRequest) {
	PageRequest pageRequest = new PageRequest(searchingRequest.getNumberPage(),searchingRequest.getSizePage());
	List<Book> booksPerCategory = repository.findAllByCategory(category);
	Collections.sort(booksPerCategory, new MyListComparator("published", "DESC"));
	int max = (searchingRequest.getSizePage()*(searchingRequest.getNumberPage()+1)>booksPerCategory.size())? booksPerCategory.size(): searchingRequest.getSizePage()*(searchingRequest.getNumberPage()+1);
	Page<Book> pageBooks1 = new PageImpl<Book>(booksPerCategory.subList(searchingRequest.getNumberPage()*searchingRequest.getSizePage(), max), pageRequest, booksPerCategory.size()); 
	Page<BookResponse> pageBookResponse = pageBooks1.map(this::convert);
	return pageBookResponse;
}

@Override
public Page<BookResponse> findAllandSortByTitleAsc(CategorySearchPageRequest request) {
	PageRequest pageRequest = new PageRequest(request.getNumberPage(),request.getSizePage());
	SearchBooksReturnPages searchingBook = new SearchBooksReturnPages(request);
	List<Book> booksPerSearch = repository.findAll(searchingBook);
	Collections.sort(booksPerSearch, new MyListComparator(sortBy, sortOrder));
	int max = (request.getSizePage()*(request.getNumberPage()+1)>booksPerSearch.size())? booksPerSearch.size(): request.getSizePage()*(request.getNumberPage()+1);
    Page<Book> pageBooks = new PageImpl<Book>(booksPerSearch.subList(request.getNumberPage()*request.getSizePage(), max), pageRequest, booksPerSearch.size()); 
    Page<BookResponse> pageBookResponse = pageBooks.map(this::convert);
		return pageBookResponse;
}

@Override
public Page<BookResponse> findAllandSortByPriceAsc(CategorySearchPageRequest request) {
	PageRequest pageRequest = new PageRequest(request.getNumberPage(),request.getSizePage(),Sort.Direction.ASC,"priceEbook");
	SearchBooksReturnPages searchingBook = new SearchBooksReturnPages(request);
	List<Book> booksPerSearch = repository.findAll(searchingBook);
	Collections.sort(booksPerSearch, new MyListComparator(sortBy1, sortOrder));
	int max = (request.getSizePage()*(request.getNumberPage()+1)>booksPerSearch.size())? booksPerSearch.size(): request.getSizePage()*(request.getNumberPage()+1);
    Page<Book> pageBooks = new PageImpl<Book>(booksPerSearch.subList(request.getNumberPage()*request.getSizePage(), max), pageRequest, booksPerSearch.size()); 
    Page<BookResponse> pageBookResponse = pageBooks.map(this::convert);
		return pageBookResponse;
}

@Override
public Page<BookResponse> findAllandSortByDatePubDesc(CategorySearchPageRequest request) {
	PageRequest pageRequest = new PageRequest(request.getNumberPage(),request.getSizePage(),Sort.Direction.DESC,"published");
	SearchBooksReturnPages searchingBook = new SearchBooksReturnPages(request);
	List<Book> booksPerSearch = repository.findAll(searchingBook);
	Collections.sort(booksPerSearch, new MyListComparator("published", "DESC"));
	int max = (request.getSizePage()*(request.getNumberPage()+1)>booksPerSearch.size())? booksPerSearch.size(): request.getSizePage()*(request.getNumberPage()+1);
    Page<Book> pageBooks = new PageImpl<Book>(booksPerSearch.subList(request.getNumberPage()*request.getSizePage(), max), pageRequest, booksPerSearch.size()); 
    Page<BookResponse> pageBookResponse = pageBooks.map(this::convert);
		return pageBookResponse;
}
}

