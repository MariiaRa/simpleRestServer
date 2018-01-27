package ua.com.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ua.com.dto.Cart;
import ua.com.dto.CartItem;
import ua.com.response.ResponseCart;
import ua.com.service.BookService;

@CrossOrigin
@RestController
@RequestMapping("/cart")
public class CartController {
@Autowired
	BookService service;
	
	@PostMapping
	@PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER')")
	public List<ResponseCart> buildCart(@RequestBody Cart cart){
		List<CartItem> itemsInCart = cart.getItems();
		List<ResponseCart> booksInCart = new ArrayList();
		for(CartItem b : itemsInCart) {
			ResponseCart response = new ResponseCart();
			response.setBook(service.findOne(b.getProductId()));
			response.setQuantity(b.getQuantity());
			response.setItemPrice(b.getItemPrice());
			booksInCart.add(response);
					}
	return booksInCart;	
	}
	
	
	
	
}
