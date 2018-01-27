package ua.com.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Region {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
			@JsonIgnore
	@OneToMany(mappedBy = "region")
	private List<Country> countries = new ArrayList<Country>();
			@JsonIgnore
	@OneToMany(mappedBy = "region")
	private List<ShippingMethod> shippingMethods = new ArrayList<ShippingMethod>();
	
	public Region() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Country> getCountries() {
		return countries;
	}

	public void setCountries(List<Country> countries) {
		this.countries = countries;
	}

	public Region(int id, String name, List<Country> countries, List<ShippingMethod> shippingMethods) {
		this.id = id;
		this.name = name;
		this.countries = countries;
		this.shippingMethods = shippingMethods;
	}

	public List<ShippingMethod> getShippingMethods() {
		return shippingMethods;
	}

	public void setShippingMethods(List<ShippingMethod> shippingMethods) {
		this.shippingMethods = shippingMethods;
	}

	@Override
	public String toString() {
		return "Region [id=" + id + ", name=" + name + ", countries=" + countries + ", shippingMethods="
				+ shippingMethods + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Region other = (Region) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	


	
	
	}
