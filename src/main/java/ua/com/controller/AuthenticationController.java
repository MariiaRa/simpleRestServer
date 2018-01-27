package ua.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ua.com.entity.User;
import ua.com.repository.UserRepository;
import ua.com.request.LoginRequest;
import ua.com.response.LoginResponse;
import ua.com.security.AuthenticationSuccessListenerImpl;
import ua.com.security.TokenUtils;

@CrossOrigin
@RestController 
// It states that all mapped methods will produce direct response output using @ResponseBody. So you don’t need to 
// put this annotation on every method. The default mapping is JSON so this is exactly what we need. 
@RequestMapping("/login")
public class AuthenticationController {

	@Value("${lgs.token.header}")
	private String tokenHeader;
	
	@Autowired 
	private AuthenticationManager authenticationManager;	
	@Autowired
	private TokenUtils tokenUtils;	
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired 
	AuthenticationSuccessListenerImpl listener;
	@Autowired
	private UserRepository repository;
	
//	@PostMapping
//	public String authenticationRequest(@RequestBody LoginRequest loginRequest ){
//		Authentication authentication = this.authenticationManager
//				.authenticate
//				(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword()));
//		SecurityContextHolder.getContext().setAuthentication(authentication);
//	
//		UserDetails userDetails = 
//				this.userDetailsService.loadUserByUsername(loginRequest.getEmail());
//		System.out.println("I am  created UserDetails object in login authenticationRequest " + userDetails);
//		String token = this.tokenUtils.generateToken(userDetails);
//		this.listener.onApplicationEvent(new AuthenticationSuccessEvent(SecurityContextHolder.getContext().getAuthentication()));
//		return token;
//	}
	
	@PostMapping
	public LoginResponse authenticationRequest2(@RequestBody LoginRequest loginRequest ){
		Authentication authentication = this.authenticationManager
				.authenticate
				(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
	
		UserDetails userDetails = 
				this.userDetailsService.loadUserByUsername(loginRequest.getEmail());
		System.out.println("I am  created UserDetails object in login authenticationRequest " + userDetails);
		String token = this.tokenUtils.generateToken(userDetails);
		User user = repository.findByEmail(loginRequest.getEmail());
		LoginResponse loginResponse = new LoginResponse(token, user.getRole(), user.getEmail());
		this.listener.onApplicationEvent(new AuthenticationSuccessEvent(SecurityContextHolder.getContext().getAuthentication()));
		return loginResponse;
	}
	
}
