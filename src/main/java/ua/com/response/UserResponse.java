package ua.com.response;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import ua.com.entity.BillingAddress;
import ua.com.entity.ShippingAddress;
import ua.com.entity.User;
import ua.com.entity.UserOrder;
//lombok
public class UserResponse {
	
	private Integer id;
	
	private String email;
	
	private String firstName;
	
	private String lastName;
	
	 private Date created;
	
	 private Date lastActive;
	 
	 private String phone;
	 
	 private List<BillingAddress> billaddresses;
	 
	 
	 private List<UserOrder> orders;
	
	public UserResponse (){}
	
	public UserResponse(User user){
		this.id = user.getId();
		this.email = user.getEmail();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.created = user.getCreated();
this.lastActive = user.getLastActive();
		this.phone = user.getPhone();
		this.billaddresses = user.getBilladdresses();
	
		this.orders = user.getOrders();
	}

	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	


	public Date getLastActive() {
		return lastActive;
	}

	public void setLastActive(Date lastActive) {
		this.lastActive = lastActive;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	

	public List<BillingAddress> getBilladdresses() {
		return billaddresses;
	}

	public void setBilladdresses(List<BillingAddress> billaddresses) {
		this.billaddresses = billaddresses;
	}


	public List<UserOrder> getOrders() {
		return orders;
	}

	public void setOrders(List<UserOrder> orders) {
		this.orders = orders;
	}

	@Override
	public String toString() {
		return "UserResponse [id=" + id + ", email=" + email + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", created=" + created + ", lastActive=" + lastActive + ", phone=" + phone + ", billaddresses="
				+ billaddresses + ", orders=" + orders + "]";
	}

	



	
}