package ua.com.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ShippingAddress {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
		
	private String addressOne;
	
	private String addressTwo;
	
	private String city;
	
	private String postcode;
	
	@ManyToOne
	private Country country;
	@JsonIgnore
	@OneToMany(mappedBy = "shipaddress", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<UserOrder> orders = new ArrayList<UserOrder>();

	public ShippingAddress(int id, String addressOne, String addressTwo, String city, String postcode,
			Country country, List<UserOrder> orders) {
		super();
		this.id = id;
		this.addressOne = addressOne;
		this.addressTwo = addressTwo;
		this.city = city;
		this.postcode = postcode;
		this.country = country;
		this.orders = orders;
	}
	public ShippingAddress() {}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAddressOne() {
		return addressOne;
	}
	public void setAddressOne(String addressOne) {
		this.addressOne = addressOne;
	}
	public String getAddressTwo() {
		return addressTwo;
	}
	public void setAddressTwo(String addressTwo) {
		this.addressTwo = addressTwo;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	public List<UserOrder> getOrders() {
		return orders;
	}
	public void setOrders(List<UserOrder> orders) {
		this.orders = orders;
	}
	@Override
	public String toString() {
		return "ShippingAddress [id=" + id + ", addressOne=" + addressOne + ", addressTwo="
				+ addressTwo + ", city=" + city + ", postcode=" + postcode + ", country=" + country + ", orders="
				+ orders + "]";
	}
	
	
	
}
