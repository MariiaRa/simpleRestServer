package ua.com.entity;

import java.math.BigDecimal;
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
public class ShippingMethod {

	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Integer id;
	
	private String method;

	@JsonIgnore
	@OneToMany(mappedBy = "shippingMethod", fetch = FetchType.LAZY)
	private List<UserOrder> userOrders = new ArrayList<UserOrder>();
	
	
//	@ManyToMany
//	@JoinTable(
//	        name = "shippingMethods_regions",
//	        joinColumns = {
//	            @JoinColumn(name = "ShippingMethod", nullable = false, updatable = false)
//	        },
//	        inverseJoinColumns = {
//	            @JoinColumn(name = "idRegion", nullable = false, updatable = false)
//	        }
//	    )
//	private List<Region> regions = new ArrayList<Region>();

	@ManyToOne
	private Region region;
	
	
	private BigDecimal price;

	public ShippingMethod() {}
	
	



	public ShippingMethod(Integer id, String method, List<UserOrder> userOrders, Region region, BigDecimal price) {
		this.id = id;
		this.method = method;
		this.userOrders = userOrders;
		this.region = region;
		this.price = price;
	}





	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getMethod() {
		return method;
	}


	public void setMethod(String method) {
		this.method = method;
	}




	public List<UserOrder> getUserOrders() {
		return userOrders;
	}





	public void setUserOrders(List<UserOrder> userOrders) {
		this.userOrders = userOrders;
	}





	public Region getRegion() {
		return region;
	}





	public void setRegion(Region region) {
		this.region = region;
	}





	public BigDecimal getPrice() {
		return price;
	}


	public void setPrice(BigDecimal price) {
		this.price = price;
	}





	@Override
	public String toString() {
		return "ShippingMethod [id=" + id + ", method=" + method + ", userOrders=" + userOrders + ", region=" + region
				+ ", price=" + price + "]";
	}


	
	
}
