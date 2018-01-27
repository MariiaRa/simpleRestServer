package ua.com.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ua.com.dto.CartItem;
import ua.com.entity.BillingAddress;
import ua.com.entity.OrderDetails;
import ua.com.entity.ShippingAddress;
import ua.com.entity.ShippingMethod;
import ua.com.entity.User;
import ua.com.entity.UserOrder;
import ua.com.repository.BillingAddressRepository;
import ua.com.repository.ShippingAddressRepository;
import ua.com.repository.UserOrderRepository;
import ua.com.repository.UserRepository;
import ua.com.request.DeleteUserRequest;
import ua.com.request.MyPageRequest;
import ua.com.request.SearchingRequest;
import ua.com.request.SubmitOrderRequest;
import ua.com.request.UpdateOrderRequest;
import ua.com.service.BillingAddressService;
import ua.com.service.BookService;
import ua.com.service.EmailService;
import ua.com.service.OrderDetailsService;
import ua.com.service.ShippingAddressService;
import ua.com.service.ShippingMethodService;
import ua.com.service.UserOrderService;


@CrossOrigin
@RestController
@RequestMapping("/order")
public class OrderController {
	@Autowired
	ShippingMethodService servicesm;
	@Autowired
	UserOrderService serviceuo;
	@Autowired
	OrderDetailsService serviceod;
	@Autowired
	UserOrderRepository repositoryo;
	@Autowired
	BookService serviceb;
	@Autowired
	UserRepository repositoryu;
	@Autowired
	ShippingAddressRepository repositoryship;
	@Autowired
	ShippingAddressService serviceship;
	@Autowired
	private EmailService emailService;
	@Autowired
	BillingAddressRepository repositorybill;
	@Autowired
	BillingAddressService servicebill;
	//PUT
	
		@PutMapping("spippingMethod/add")
		@PreAuthorize("hasRole('ROLE_USER')")
		public ShippingMethod addShippigMethod(@RequestBody ShippingMethod shippingMethod){
			return servicesm.save(shippingMethod);
		}
	
		@PutMapping
		@PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER')")
		public UserOrder addUserOrder(@RequestBody SubmitOrderRequest submitOrderRequest) {
			UserOrder userOrder = new UserOrder();
			
			if (submitOrderRequest.getBilladdress().getId() == 0) {
		BillingAddress billingAddress = repositorybill.saveAndFlush(submitOrderRequest.getBilladdress());
		billingAddress.setUser(repositoryu.findByEmail(submitOrderRequest.getEmail()));
		servicebill.save(billingAddress);
		userOrder.setBilladdress(billingAddress);
			}else {
				userOrder.setBilladdress(submitOrderRequest.getBilladdress());
			}

			userOrder.setOrderDate(new Date());
			userOrder.setOrderStatus("submitted");
		ShippingAddress shippingAddress = repositoryship.saveAndFlush(submitOrderRequest.getShipaddress());
			userOrder.setShipaddress(shippingAddress);
			userOrder.setOrderTotal(submitOrderRequest.getOrderTotal());
			repositoryo.saveAndFlush(userOrder);
			List<CartItem> itemsInCart = submitOrderRequest.getCart().getItems();
			List<OrderDetails> orderDetails = new ArrayList<OrderDetails>();
			for(CartItem b : itemsInCart) {
				OrderDetails orderDetail = new OrderDetails();
				orderDetail.setBook(serviceb.findOne(b.getProductId()));
				orderDetail.setBookQuantity(b.getQuantity());
				orderDetail.setUserOrder(userOrder);
				orderDetails.add(orderDetail);
				orderDetail.setItemPrice(b.getItemPrice());
						}
			userOrder.setOrderDetails(orderDetails);
			userOrder.setShippingMethod(submitOrderRequest.getShippingMethod());
			userOrder.setUser(repositoryu.findByEmail(submitOrderRequest.getEmail()));
			
			serviceship.save(shippingAddress);
			serviceuo.save(userOrder);
			User user = repositoryu.findByEmail(submitOrderRequest.getEmail());
			SimpleMailMessage orderEmail = new SimpleMailMessage();
			orderEmail.setFrom("mind.is.still@gmail.com");
			orderEmail.setTo(submitOrderRequest.getEmail());
			orderEmail.setSubject("Your Order #"+ userOrder.getId());
			orderEmail.setText("Hi, "+user.getFirstName()+" "+user.getLastName()+"!" +"\n\nYour order has been submitted. Our manager will contact you soon regarding payment."
					+ " Thank you for choosing OverCoded!" +"\n"+ "\n\nSincerely, OverCoded team.");
			emailService.sendEmail(orderEmail);
			
			return userOrder;
		}
		
		//POST
	
	//update order status admin
		@PostMapping("/page")
		@PreAuthorize("hasRole('ROLE_ADMIN')")
		public Page<UserOrder> users(@RequestBody MyPageRequest myPageRequest){
			return serviceuo.findAll(myPageRequest);
		}
		
		@PostMapping("/update")
		@PreAuthorize("hasRole('ROLE_ADMIN')")
		public boolean updateOrder(@RequestBody UpdateOrderRequest request) {
			UserOrder order = serviceuo.findOne(request.getId());
			order.setOrderStatus(request.getStatus());
			order.setShippingDate(request.getShippingDate());
			repositoryo.save(order);
			
			
			// Lookup user and send notification email
		User user = order.getUser();
		SimpleMailMessage orderEmail = new SimpleMailMessage();
		orderEmail.setFrom("mind.is.still@gmail.com");
		orderEmail.setTo(user.getEmail());
		orderEmail.setSubject("Order Updates");
		orderEmail.setText("Hi, "+user.getFirstName()+" "+user.getLastName()+"!" +"\n\nYour order has been shipped. "
				+ "Thank you for choosing OverCoded!" +"\n"+ "\n\nSincerely, OverCoded team.");
		emailService.sendEmail(orderEmail);
		return true;
		}
		
		@PostMapping("/search")
		@PreAuthorize("hasRole('ROLE_ADMIN')")
		public List<UserOrder> orderSearch(@RequestBody SearchingRequest searchingRequest){
			return serviceuo.findAll(searchingRequest);
		}
	
	
		//GET
	//get my orders user
		@GetMapping
		@PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER')")
		public List<UserOrder> getUserOrders(){
			return serviceuo.findAll();
		}
	
		
		@GetMapping("/shippingMethods")
		@PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER')")
		public List<ShippingMethod> getShippingMethods(){
			return servicesm.findAll();
		}

		@GetMapping("/orderDetails")
		@PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER')")
		public List<OrderDetails> getOrderDetails(){
			return serviceod.findAll();
		}
		
		//DELETE
		@DeleteMapping("spippingMethod/delete")
		@PreAuthorize("hasRole('ROLE_ADMIN')")
		public boolean deleteShippingMethod(@RequestBody DeleteUserRequest request) {
			return servicesm.delete(request.getId());
		}
				
		@DeleteMapping
		public boolean deleteUserOrder(@RequestBody DeleteUserRequest request) {
			return serviceuo.delete(request.getId());
		}
	
	
}
