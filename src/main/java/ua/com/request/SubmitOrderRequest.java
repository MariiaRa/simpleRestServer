package ua.com.request;

import java.math.BigDecimal;
import java.util.List;

import ua.com.dto.Cart;
import ua.com.entity.BillingAddress;
import ua.com.entity.OrderDetails;
import ua.com.entity.ShippingAddress;
import ua.com.entity.ShippingMethod;

public class SubmitOrderRequest {
private String email;
private BigDecimal orderTotal;
private BillingAddress billaddress;
private ShippingAddress shipaddress;
private Cart cart;
private ShippingMethod shippingMethod;
public SubmitOrderRequest() {
	}
public SubmitOrderRequest(String email, BigDecimal orderTotal, BillingAddress billaddress, ShippingAddress shipaddress,
		Cart cart, ShippingMethod shippingMethod) {
	this.email = email;
	this.orderTotal = orderTotal;
	this.billaddress = billaddress;
	this.shipaddress = shipaddress;
	this.cart = cart;
	this.shippingMethod = shippingMethod;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public BigDecimal getOrderTotal() {
	return orderTotal;
}
public void setOrderTotal(BigDecimal orderTotal) {
	this.orderTotal = orderTotal;
}
public BillingAddress getBilladdress() {
	return billaddress;
}
public void setBilladdress(BillingAddress billaddress) {
	this.billaddress = billaddress;
}
public ShippingAddress getShipaddress() {
	return shipaddress;
}
public void setShipaddress(ShippingAddress shipaddress) {
	this.shipaddress = shipaddress;
}

public Cart getCart() {
	return cart;
}
public void setCart(Cart cart) {
	this.cart = cart;
}
public ShippingMethod getShippingMethod() {
	return shippingMethod;
}
public void setShippingMethod(ShippingMethod shippingMethod) {
	this.shippingMethod = shippingMethod;
}
@Override
public String toString() {
	return "SubmitOrderRequest [email=" + email + ", orderTotal=" + orderTotal + ", billaddress=" + billaddress
			+ ", shipaddress=" + shipaddress + ", cart=" + cart + ", shippingMethod=" + shippingMethod + "]";
}





}
