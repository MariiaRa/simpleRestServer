package ua.com.request;

import java.math.BigDecimal;

import javax.persistence.Column;

public class BookUpdateRequest {
	private int id;
	
	private int stock;
	
	private BigDecimal priceEbook;
	
	private BigDecimal priceprintedbook;
	
private String bookCoverImage;
	
	private String bookBriefDescription;

	private String bookFullDescription;

	private boolean status;
	
	private String feature;
	
	public BookUpdateRequest(int id, int stock, BigDecimal priceEbook, BigDecimal priceprintedbook,
			String bookCoverImage, String bookBriefDescription, String bookFullDescription, boolean status, String feature) {
				this.id = id;
		this.stock = stock;
		this.priceEbook = priceEbook;
		this.priceprintedbook = priceprintedbook;
		this.bookCoverImage = bookCoverImage;
		this.bookBriefDescription = bookBriefDescription;
		this.bookFullDescription = bookFullDescription;
		this.status = status;
		this.feature = feature;
		
	}
	
	public BookUpdateRequest() {}

	
	
	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
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

	
	

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "BookUpdateRequest [id=" + id + ", stock=" + stock + ", priceEbook=" + priceEbook + ", priceprintedbook="
				+ priceprintedbook + ", bookCoverImage=" + bookCoverImage + ", bookBriefDescription="
				+ bookBriefDescription + ", bookFullDescription=" + bookFullDescription + ", active=" + status
				+ ", feature=" + feature + "]";
	}

	

	
	
	
	
}
