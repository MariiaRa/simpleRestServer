package ua.com.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="bookId", unique = true, nullable = false)
	private int id;
		
	private String title;
	
	@ManyToMany
	@JoinTable(
	        name = "books_authors",
	        joinColumns = {
	            @JoinColumn(name = "idBook", nullable = false, updatable = false)
	        },
	        inverseJoinColumns = {
	            @JoinColumn(name = "idAuthor", nullable = false, updatable = false)
	        }
	    )
	private List<Author> authors = new ArrayList<Author>();

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	private int pages;
	private int stock;
	
	
	
	
	@ManyToOne
	@JoinColumn(name="publisherId")
	private Publisher publisher;
	
	
	private String isbn;
	
	@Temporal(TemporalType.DATE)
  	 private Date published = new Date();
	
		
	@ManyToOne
	private Category category;
	
	private BigDecimal priceEbook;
	private BigDecimal priceprintedbook;
	private boolean status=true;
	
	private String feature;
	


	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
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

	private String bookCoverImage;
	
	private String bookBriefDescription;
	
	@Column(columnDefinition="text")
	private String bookFullDescription;
	@JsonIgnore
	@OneToMany(mappedBy = "book")
	private List<OrderDetails> orderDetails = new ArrayList<OrderDetails>();



	public Book(int id, String title, List<Author> authors, int pages, int stock, Publisher publisher, String isbn,
			Date published, Category category, BigDecimal priceEbook, BigDecimal priceprintedbook, boolean status,
			String feature, String bookCoverImage, String bookBriefDescription, String bookFullDescription,
			List<OrderDetails> orderDetails) {
		this.id = id;
		this.title = title;
		this.authors = authors;
		this.pages = pages;
		this.stock = stock;
		this.publisher = publisher;
		this.isbn = isbn;
		this.published = published;
		this.category = category;
		this.priceEbook = priceEbook;
		this.priceprintedbook = priceprintedbook;
		this.status = status;
		this.feature = feature;
		this.bookCoverImage = bookCoverImage;
		this.bookBriefDescription = bookBriefDescription;
		this.bookFullDescription = bookFullDescription;
		this.orderDetails = orderDetails;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public List<OrderDetails> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetails> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public Book() {}
	


	public Date getPublished() {
		return published;
	}

	public void setPublished(Date published) {
		this.published = published;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getBookFullDescription() {
		return bookFullDescription;
	}

	public void setBookFullDescription(String bookFullDescription) {
		this.bookFullDescription = bookFullDescription;
	}
	

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}



	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", authors=" + authors + ", pages=" + pages + ", stock=" + stock
				+ ", publisher=" + publisher + ", isbn=" + isbn + ", published=" + published + ", category=" + category
				+ ", priceEbook=" + priceEbook + ", priceprintedbook=" + priceprintedbook + ", status=" + status
				+ ", feature=" + feature + ", bookCoverImage=" + bookCoverImage + ", bookBriefDescription="
				+ bookBriefDescription + ", bookFullDescription=" + bookFullDescription + ", orderDetails="
				+ orderDetails + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (status ? 1231 : 1237);
		result = prime * result + ((authors == null) ? 0 : authors.hashCode());
		result = prime * result + ((bookBriefDescription == null) ? 0 : bookBriefDescription.hashCode());
		result = prime * result + ((bookCoverImage == null) ? 0 : bookCoverImage.hashCode());
		result = prime * result + ((bookFullDescription == null) ? 0 : bookFullDescription.hashCode());
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((feature == null) ? 0 : feature.hashCode());
		result = prime * result + id;
		result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
		result = prime * result + ((orderDetails == null) ? 0 : orderDetails.hashCode());
		result = prime * result + pages;
		result = prime * result + ((priceEbook == null) ? 0 : priceEbook.hashCode());
		result = prime * result + ((priceprintedbook == null) ? 0 : priceprintedbook.hashCode());
		result = prime * result + ((published == null) ? 0 : published.hashCode());
		result = prime * result + ((publisher == null) ? 0 : publisher.hashCode());
		result = prime * result + stock;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (status != other.status)
			return false;
		if (authors == null) {
			if (other.authors != null)
				return false;
		} else if (!authors.equals(other.authors))
			return false;
		if (bookBriefDescription == null) {
			if (other.bookBriefDescription != null)
				return false;
		} else if (!bookBriefDescription.equals(other.bookBriefDescription))
			return false;
		if (bookCoverImage == null) {
			if (other.bookCoverImage != null)
				return false;
		} else if (!bookCoverImage.equals(other.bookCoverImage))
			return false;
		if (bookFullDescription == null) {
			if (other.bookFullDescription != null)
				return false;
		} else if (!bookFullDescription.equals(other.bookFullDescription))
			return false;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (feature == null) {
			if (other.feature != null)
				return false;
		} else if (!feature.equals(other.feature))
			return false;
		if (id != other.id)
			return false;
		if (isbn == null) {
			if (other.isbn != null)
				return false;
		} else if (!isbn.equals(other.isbn))
			return false;
		if (orderDetails == null) {
			if (other.orderDetails != null)
				return false;
		} else if (!orderDetails.equals(other.orderDetails))
			return false;
		if (pages != other.pages)
			return false;
		if (priceEbook == null) {
			if (other.priceEbook != null)
				return false;
		} else if (!priceEbook.equals(other.priceEbook))
			return false;
		if (priceprintedbook == null) {
			if (other.priceprintedbook != null)
				return false;
		} else if (!priceprintedbook.equals(other.priceprintedbook))
			return false;
		if (published == null) {
			if (other.published != null)
				return false;
		} else if (!published.equals(other.published))
			return false;
		if (publisher == null) {
			if (other.publisher != null)
				return false;
		} else if (!publisher.equals(other.publisher))
			return false;
		if (stock != other.stock)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}



	



	

	

	



}
