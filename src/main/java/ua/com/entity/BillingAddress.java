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
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class BillingAddress {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@JsonIgnore
	@ManyToOne
	private User user;
	
	private String addressOne;
	
	private String addressTwo;
	
	private String city;
	
	private String postcode;
	
	@ManyToOne
	private Country country;
	@JsonIgnore
	@OneToMany(mappedBy = "billaddress", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<UserOrder> orders = new ArrayList<UserOrder>();
	
	public BillingAddress() {}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public BillingAddress(int id, User user, String addressOne, String addressTwo, String city, String postcode,
			Country country, List<UserOrder> orders) {
		super();
		this.id = id;
		this.user = user;
		this.addressOne = addressOne;
		this.addressTwo = addressTwo;
		this.city = city;
		this.postcode = postcode;
		this.country = country;
		this.orders = orders;
		}

	@Override
	public String toString() {
		return "BillingAddress [id=" + id + ", user=" + user + ", addressOne=" + addressOne + ", addressTwo="
				+ addressTwo + ", city=" + city + ", postcode=" + postcode + ", country=" + country + ", orders="
				+ orders + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((addressOne == null) ? 0 : addressOne.hashCode());
		result = prime * result + ((addressTwo == null) ? 0 : addressTwo.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((postcode == null) ? 0 : postcode.hashCode());
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
		BillingAddress other = (BillingAddress) obj;
		if (addressOne == null) {
			if (other.addressOne != null)
				return false;
		} else if (!addressOne.equals(other.addressOne))
			return false;
		if (addressTwo == null) {
			if (other.addressTwo != null)
				return false;
		} else if (!addressTwo.equals(other.addressTwo))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (postcode == null) {
			if (other.postcode != null)
				return false;
		} else if (!postcode.equals(other.postcode))
			return false;
		return true;
	}



	
}
