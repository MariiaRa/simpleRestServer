package ua.com.response;

import java.math.BigDecimal;
import java.util.List;

import ua.com.entity.Book;

public class ResponseCart {
private Book book;
private Integer quantity;
private BigDecimal itemPrice;

public ResponseCart() {}

public ResponseCart(Book book, Integer quantity, BigDecimal itemPrice) {
	super();
	this.book = book;
	this.quantity = quantity;
	this.itemPrice = itemPrice;
}

public Book getBook() {
	return book;
}

public void setBook(Book book) {
	this.book = book;
}

public int getQuantity() {
	return quantity;
}

public void setQuantity(Integer quantity) {
	this.quantity = quantity;
}

public BigDecimal getItemPrice() {
	return itemPrice;
}

public void setItemPrice(BigDecimal itemPrice) {
	this.itemPrice = itemPrice;
}

@Override
public String toString() {
	return "ResponseCart [book=" + book + ", quantity=" + quantity + ", itemPrice=" + itemPrice + "]";
}



}
