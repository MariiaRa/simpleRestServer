package ua.com.request;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class UpdateOrderRequest {
private Integer id;
private String status;
//@Temporal(TemporalType.DATE)
private Date shippingDate;
public UpdateOrderRequest(Integer id, String status, Date shippingDate) {
	this.id = id;
	this.status = status;
	this.shippingDate = shippingDate;
}
public UpdateOrderRequest() {}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public Date getShippingDate() {
	return shippingDate;
}
public void setShippingDate(Date shippingDate) {
	this.shippingDate = shippingDate;
}




}
