package ua.com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ua.com.entity.BillingAddress;
import ua.com.entity.Country;
import ua.com.entity.Region;
import ua.com.entity.Role;
import ua.com.entity.ShippingAddress;
import ua.com.entity.User;
import ua.com.repository.BillingAddressRepository;
import ua.com.repository.UserRepository;
import ua.com.request.AddressRequest;
import ua.com.request.DeleteUserRequest;
import ua.com.request.LoginRequest;
import ua.com.request.MyPageRequest;
import ua.com.request.SearchingRequest;
import ua.com.request.UpdatePassRequest;
import ua.com.request.UserEmail;
import ua.com.request.UserUpdateRequest;
import ua.com.response.UserResponse;
import ua.com.service.BillingAddressService;
import ua.com.service.CountryService;
import ua.com.service.EmailService;
import ua.com.service.RegionService;
import ua.com.service.ShippingAddressService;
import ua.com.service.UserService;
import ua.com.validation.EmailExistsException;


@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository repository;
	@Autowired
	BillingAddressService servicebill;
	@Autowired
	private RegionService servicer;
	@Autowired
	private CountryService servicec;
	@Autowired
	private ShippingAddressService serviceship;
	@Autowired
	private BillingAddressRepository repositorybill;
	@Autowired
	private EmailService emailService;
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/page")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
    public Page<UserResponse> users(@RequestBody MyPageRequest myPageRequest){
		return userService.findAll(myPageRequest);
	}
	
	@PostMapping("/search")
	@PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER')")
	public List<User> userSearch(@RequestBody SearchingRequest searchingRequest){
		return userService.findAll(searchingRequest);
	}

	@PostMapping
	public boolean login(@RequestBody LoginRequest loginRequest){
		return userService.login(loginRequest);
	}
	
	@PostMapping("/update")
	@PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER')")
	public boolean updateProfile(@RequestBody UserUpdateRequest request) {
		User user = repository.findByEmail(request.getEmail());
		user.setFirstName(request.getFirstName());
		user.setLastName(request.getLastName());
		user.setPhone(request.getPhone());
	userRepository.save(user);
		return true;
	}
	
	@PostMapping("/update/pass")
	@PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER')")
	public boolean updateProfile(@RequestBody UpdatePassRequest request){
		User user = repository.findByEmail(request.getEmail());
		user.setPassword(passwordEncoder.encode(request.getNewPass()));		
		userRepository.save(user);
		return true;
		}
	
	@PostMapping("/update/billaddress")
	@PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER')")
	public boolean updateAddress(@RequestBody AddressRequest request){
		 BillingAddress billaddress = servicebill.findOne(request.getId());
		 billaddress.setAddressOne(request.getAddressOne());
		 billaddress.setAddressTwo(request.getAddressTwo());
		 billaddress.setCity(request.getCity());
		 billaddress.setPostcode(request.getPostcode());
		 billaddress.setCountry(request.getCountry());
		 servicebill.save(billaddress);
		return true;
		}
	

	@PostMapping("/verification/email")
	public boolean emailVerification(@RequestBody LoginRequest loginRequest) throws EmailExistsException {
		User user = repository.findByEmail(loginRequest.getEmail());
		user.setPassword(passwordEncoder.encode(loginRequest.getPassword()));
		user.setRole(Role.ROLE_USER);
		userRepository.save(user);
		return true;
	}
	
	
	 // Process form submission from forgotPassword page
		@PostMapping("/forgot/password")
		public boolean sendResetPasswordLink(@RequestBody UserEmail email) throws IOException {

	// Lookup user in database by e-mail
	User user = repository.findByEmail(email.getEmail());
	// Email message		
	String url = "file:///C:/Users/user/Desktop/ajax%20(2)/ajax/restorePass.html";
	SimpleMailMessage passwordEmail = new SimpleMailMessage();
					passwordEmail.setFrom("mind.is.still@gmail.com");
					passwordEmail.setTo(user.getEmail());
					passwordEmail.setSubject("Password Request");
					passwordEmail.setText("Hi, "+user.getFirstName()+" "+user.getLastName()+"!" + "\n\nReset your password here: " + url + "."
							+"\n\nSincerely, OverCoded team." );
//					user.getPassword());
emailService.sendEmail(passwordEmail);
									
//									MimeMessage message = mailSender.createMimeMessage();
//
//									   try{
//										MimeMessageHelper helper = new MimeMessageHelper(message, true);
//
//										helper.setFrom(passwordEmail.getFrom());
//										helper.setTo(passwordEmail.getTo());
//										helper.setSubject(passwordEmail.getSubject());
//										helper.setText("Reset your password here: " + url + ".");
//										Path pdfPath = Paths.get("C:\\Users\\user\\Downloads\\Spring Boot in Action.pdf");
//								        byte[] pdf = Files.readAllBytes(pdfPath);
//								        
//								        helper.addAttachment("my.pdf", new ByteArrayResource(pdf));
////										FileSystemResource file = new FileSystemResource("C:\\Users\\user\\Downloads\\Spring Boot in Action.pdf");
////										helper.addAttachment(file.getFilename(), file);
//									
//									
//
//									     }catch (MessagingException e) {
//										throw new MailParseException(e);
//									     }
//									     mailSender.send(message);
								        
									return true;
							}
		
		@PostMapping("/reset/password")
		public boolean resetPassword(@RequestBody LoginRequest request){
			User user = repository.findByEmail(request.getEmail());
			user.setPassword(passwordEncoder.encode(request.getPassword()));		
		    userRepository.save(user);
			return true;			
					}
		
		
	
	//PUT
	
	@PutMapping
	public User register(@RequestBody User user) throws EmailExistsException{
		
//	System.out.println(user + "registered");
//							String url = "file:///C:/Users/user/Desktop/ajax%20(2)/ajax/setPass.html";
//							// Email message
//							SimpleMailMessage passwordEmail = new SimpleMailMessage();
//							passwordEmail.setFrom("mind.is.still@gmail.com");
//							passwordEmail.setTo(user.getEmail());
//							passwordEmail.setSubject("Account setup");
//							passwordEmail.setText("Hi, "+user.getFirstName()+" "+user.getLastName()+"!" +"\n\nUse the link below to verify your email "
//									+ "and complete your user account setup. \n\nThank you and welcome to OverCoded!" +"\n"+ url + "\n\nSincerely, OverCoded team.");
//											emailService.sendEmail(passwordEmail);
				return userService.register(user);
	}
	
	@PutMapping("/region/add")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Region addRegion(@RequestBody Region region){
		return servicer.save(region);
	}
	
	@PutMapping("billaddress/add")
	@PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER')")
	public BillingAddress addBillAddress(@RequestBody BillingAddress billAddress){
		User user = repository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		billAddress = repositorybill.saveAndFlush(billAddress);
		billAddress.setUser(user);
		return servicebill.save(billAddress);
	}
	
	@PutMapping("/country/add")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Country addCountry(@RequestBody Country country){
		return servicec.save(country);
	}
	
	@PutMapping("shipaddress/add")
	@PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER')")
	public ShippingAddress addShipAddress(@RequestBody ShippingAddress shipAddress){
	return serviceship.save(shipAddress);
	}
	
	//GET
	@PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER')")
		@GetMapping("/regions")
		public List<Region> regions(){
			return servicer.findAll();
		}
			
		
		@GetMapping("/countries")
		@PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER')")
		public List<Country> countries(){
			return servicec.findAll();
		}
		
		@GetMapping("/billaddresses")
		@PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER')")
		public List<BillingAddress> billaddresses(){
			return servicebill.findAll();
		}
		
		@GetMapping("/shipaddresses")
		@PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER')")
		public List<ShippingAddress> shipaddresses(){
			return serviceship.findAll();
		}
	
		//DELETE
		
		
	@DeleteMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	//@PreAuthorize annotation is very important for this example. This controller will be extended by AOP and every of its 
	//methods will require for the current request to be authenticated with principal that has GrantedAuthority: 
	//ROLE_DOMAIN_USER. SecurityContextHolder will be queried to get this information. For @PreAuthorize annotation 
	//to have effect there is a need to have @EnableGlobalMethodSecurity annotation on @Configuration bean
	public boolean delete(@RequestBody DeleteUserRequest request){
		return userService.delete(request.getId());
	}
	
	//DELETE
			
		
		//delete shippingaddress user
		@DeleteMapping("/billaddress/delete")
		@PreAuthorize("hasRole('ROLE_USER')")
		public boolean deleteShippingAddress(@RequestBody DeleteUserRequest request) {
			return servicebill.delete(request.getId());
		}
	
}
