package ua.com.dto;

import java.math.BigDecimal;

public class CartItem {

    private Integer productId;
    private Integer quantity;
    private BigDecimal itemPrice;
    public CartItem() {}
    
	public CartItem(Integer productId, Integer quantity, BigDecimal itemPrice) {
		this.productId = productId;
		this.quantity = quantity;
		this.itemPrice = itemPrice;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
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
		return "CartItem [productId=" + productId + ", quantity=" + quantity + ", itemPrice=" + itemPrice + "]";
	}

	
   
}
