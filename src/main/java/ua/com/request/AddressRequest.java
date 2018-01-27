package ua.com.request;

import ua.com.entity.Country;

public class AddressRequest {
	
	private int id;
private String addressOne;
	
	private String addressTwo;
	
	private String city;
	
	private String postcode;
	
	private Country country;

	public AddressRequest(int id, String addressOne, String addressTwo, String city, String postcode,
			Country country) {
				this.id = id;
		this.addressOne = addressOne;
		this.addressTwo = addressTwo;
		this.city = city;
		this.postcode = postcode;
		this.country = country;
	}

	public AddressRequest() {}



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

	@Override
	public String toString() {
		return "AddressRequest [id=" + id + ", addressOne=" + addressOne + ", addressTwo=" + addressTwo + ", city="
				+ city + ", postcode=" + postcode + ", country=" + country + "]";
	}

	
	
	
	
	
}
