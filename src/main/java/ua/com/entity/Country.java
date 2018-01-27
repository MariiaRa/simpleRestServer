package ua.com.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Country {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;
	
	@ManyToOne
	private Region region;
	
	@JsonIgnore
	@OneToMany(mappedBy = "country")
	private List<BillingAddress> billaddresses = new ArrayList<BillingAddress>();
	@JsonIgnore
	@OneToMany(mappedBy = "country")
	private List<ShippingAddress> shipaddresses = new ArrayList<ShippingAddress>();
	
	public Country() {}

	public Country(int id, String name, Region region, List<BillingAddress> billaddresses,
			List<ShippingAddress> shipaddresses) {
		this.id = id;
		this.name = name;
		this.region = region;
		this.billaddresses = billaddresses;
		this.shipaddresses = shipaddresses;
	}

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

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public List<BillingAddress> getBilladdresses() {
		return billaddresses;
	}

	public void setBilladdresses(List<BillingAddress> billaddresses) {
		this.billaddresses = billaddresses;
	}

	public List<ShippingAddress> getShipaddresses() {
		return shipaddresses;
	}

	public void setShipaddresses(List<ShippingAddress> shipaddresses) {
		this.shipaddresses = shipaddresses;
	}

	@Override
	public String toString() {
		return "Country [id=" + id + ", name=" + name + ", region=" + region + ", billaddresses=" + billaddresses
				+ ", shipaddresses=" + shipaddresses + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((region == null) ? 0 : region.hashCode());
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
		Country other = (Country) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (region == null) {
			if (other.region != null)
				return false;
		} else if (!region.equals(other.region))
			return false;
		return true;
	}

	
}
