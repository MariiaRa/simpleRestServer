package ua.com.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class OrderDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@JsonIgnore
	@ManyToOne
private UserOrder userOrder;
//	@JsonIgnore
	@ManyToOne
	private Book book;
	
	private int bookQuantity;
	 private BigDecimal itemPrice;
	
	public OrderDetails() {}

	public OrderDetails(int id, UserOrder userOrder, Book book, int bookQuantity, BigDecimal itemPrice) {
		this.id = id;
		this.userOrder = userOrder;
		this.book = book;
		this.bookQuantity = bookQuantity;
		this.itemPrice = itemPrice;
	}

	public BigDecimal getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(BigDecimal itemPrice) {
		this.itemPrice = itemPrice;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public UserOrder getUserOrder() {
		return userOrder;
	}

	public void setUserOrder(UserOrder userOrder) {
		this.userOrder = userOrder;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public int getBookQuantity() {
		return bookQuantity;
	}

	public void setBookQuantity(int bookQuantity) {
		this.bookQuantity = bookQuantity;
	}

	@Override
	public String toString() {
		return "OrderDetails [id=" + id + ", userOrder=" + userOrder + ", book=" + book + ", bookQuantity="
				+ bookQuantity + ", itemPrice=" + itemPrice + "]";
	}
	
}
