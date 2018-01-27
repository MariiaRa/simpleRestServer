package ua.com.response;

import java.math.BigDecimal;
import java.util.List;

import ua.com.entity.Author;
import ua.com.entity.Book;
import ua.com.entity.Category;
import ua.com.entity.Publisher;
import ua.com.entity.User;

public class BookResponse {

	private int id;
	
	private String title;
	
	private List<Author> authors;
	
	private Publisher publisher;
	
	private Category category;
	
	private int stock;
	
	private BigDecimal priceEbook;
	private BigDecimal priceprintedbook;
	
	private String bookCoverImage;
	private String bookBriefDescription;
	
	private boolean status;
	
	private String feature;
		
	public BookResponse() {}


	public BookResponse(Book book){
		this.id = book.getId();
		this.title = book.getTitle();
		this.authors = book.getAuthors();
		this.publisher = book.getPublisher();
		this.category = book.getCategory();
		this.priceEbook = book.getPriceEbook();
		this.priceprintedbook = book.getPriceprintedbook();
		this.stock = book.getStock();
		this.bookCoverImage = book.getBookCoverImage();
		this.bookBriefDescription = book.getBookBriefDescription();
		this.status = book.isStatus();
		this.feature = book.getFeature();
	}
	
	
	public String getFeature() {
		return feature;
	}


	public void setFeature(String feature) {
		this.feature = feature;
	}


	public boolean isStatus() {
		return status;
	}


	public void setStatus(boolean status) {
		this.status = status;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public BigDecimal getPriceEbook() {
		return priceEbook;
	}

	public void setPriceEbook(BigDecimal priceEbook) {
		this.priceEbook = priceEbook;
	}

	public BigDecimal getPriceprintedbook() {
		return priceprintedbook;
	}

	public void setPriceprintedbook(BigDecimal priceprintedbook) {
		this.priceprintedbook = priceprintedbook;
	}


	public int getStock() {
		return stock;
	}


	public void setStock(int stock) {
		this.stock = stock;
	}


	public String getBookCoverImage() {
		return bookCoverImage;
	}


	public void setBookCoverImage(String bookCoverImage) {
		this.bookCoverImage = bookCoverImage;
	}

	

	public String getBookBriefDescription() {
		return bookBriefDescription;
	}


	public void setBookBriefDescription(String bookBriefDescription) {
		this.bookBriefDescription = bookBriefDescription;
	}


	@Override
	public String toString() {
		return "BookResponse [id=" + id + ", title=" + title + ", authors=" + authors + ", publisher=" + publisher
				+ ", category=" + category + ", stock=" + stock + ", priceEbook=" + priceEbook + ", priceprintedbook="
				+ priceprintedbook + ", bookCoverImage=" + bookCoverImage + ", bookBriefDescription="
				+ bookBriefDescription + ", status=" + status + ", feature=" + feature + "]";
	}









	
	
	
}
