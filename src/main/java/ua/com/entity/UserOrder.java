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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class UserOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Temporal(TemporalType.DATE)
	private Date orderDate = new Date();
//	@JsonIgnore
	@ManyToOne
	private User user;
	
	private BigDecimal orderTotal;
	
	private String orderStatus;
	
	
	 @ManyToOne
		private BillingAddress billaddress;
	
	 @ManyToOne
		private ShippingAddress shipaddress;
	 
//	 @JsonIgnore
	 @OneToMany(mappedBy = "userOrder", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
		private List<OrderDetails> orderDetails = new ArrayList<OrderDetails>();
	 
	 @ManyToOne
	 private ShippingMethod shippingMethod;
	
	
	public UserOrder() {}

	@Temporal(TemporalType.DATE)
	private Date shippingDate;
	
	
	public UserOrder(int id, Date orderDate, User user, BigDecimal orderTotal, String orderStatus,
			BillingAddress billaddress, ShippingAddress shipaddress, List<OrderDetails> orderDetails,
			ShippingMethod shippingMethod, Date shippingDate) {
		this.id = id;
		this.orderDate = orderDate;
		this.user = user;
		this.orderTotal = orderTotal;
		this.orderStatus = orderStatus;
		this.billaddress = billaddress;
		this.shipaddress = shipaddress;
		this.orderDetails = orderDetails;
		this.shippingMethod = shippingMethod;
		this.shippingDate = shippingDate;
			}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Date getOrderDate() {
		return orderDate;
	}


	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public BigDecimal getOrderTotal() {
		return orderTotal;
	}


	public void setOrderTotal(BigDecimal orderTotal) {
		this.orderTotal = orderTotal;
	}


	public String getOrderStatus() {
		return orderStatus;
	}


	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
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


	public List<OrderDetails> getOrderDetails() {
		return orderDetails;
	}


	public void setOrderDetails(List<OrderDetails> orderDetails) {
		this.orderDetails = orderDetails;
	}


	public ShippingMethod getShippingMethod() {
		return shippingMethod;
	}


	public void setShippingMethod(ShippingMethod shippingMethod) {
		this.shippingMethod = shippingMethod;
	}


	public Date getShippingDate() {
		return shippingDate;
	}


	public void setShippingDate(Date shippingDate) {
		this.shippingDate = shippingDate;
	}


	@Override
	public String toString() {
		return "UserOrder [id=" + id + ", orderDate=" + orderDate + ", user=" + user + ", orderTotal=" + orderTotal
				+ ", orderStatus=" + orderStatus + ", billaddress=" + billaddress + ", shipaddress=" + shipaddress
				+ ", orderDetails=" + orderDetails + ", shippingMethod=" + shippingMethod + ", shippingDate="
				+ shippingDate + "]";
	}
	 
	 
	
}
