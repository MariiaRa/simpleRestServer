package ua.com.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ua.com.entity.User;
import ua.com.repository.UserRepository;
import ua.com.request.LoginRequest;
import ua.com.request.MyPageRequest;
import ua.com.request.SearchingRequest;
import ua.com.response.UserResponse;
import ua.com.service.EmailService;
import ua.com.service.UserService;
import ua.com.specification.SearchingUser;
import ua.com.validation.EmailExistsException;

@Service
public class UserServiceImpl implements UserService, UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private EmailService emailService;
	@Override
	public User register(User user) throws EmailExistsException{
		if (emailExist(user.getEmail())) {
	        throw new EmailExistsException(
	          "There is an account with that email adress:" + user.getEmail());
	    }else {
		String url = "file:///C:/Users/user/Desktop/ajax%20(2)/ajax/setPass.html";
		// Email message
		SimpleMailMessage passwordEmail = new SimpleMailMessage();
		passwordEmail.setFrom("mind.is.still@gmail.com");
		passwordEmail.setTo(user.getEmail());
		passwordEmail.setSubject("Account setup");
		passwordEmail.setText("Hi, "+user.getFirstName()+" "+user.getLastName()+"!" +"\n\nUse the link below to verify your email "
				+ "and complete your user account setup. \n\nThank you and welcome to OverCoded!" +"\n"+ url + "\n\nSincerely, OverCoded team.");
						emailService.sendEmail(passwordEmail);
		return  userRepository.save(user);
	    }
	}

	@Override
	public boolean login(LoginRequest loginRequest) {
		if(loginRequest!=null){
			User user  = userRepository.findByEmail(loginRequest.getEmail());
			if(user!=null){
				if(user.getPassword().equals(loginRequest.getPassword())){
					return true;
				}else{
					throw new IllegalArgumentException("Login or password is incorrect");
				}
			}else{
				throw new IllegalArgumentException("Login or password is incorrect");
			}
		}else{
			throw new IllegalArgumentException("LoginRequest not be null");
		}
	}

	@Override
	public Page<UserResponse> findAll(MyPageRequest page) {
		PageRequest pageRequest = new PageRequest(page.getNumberPage(),page.getSizePage());
		Page<User> pageUsers =  userRepository.findAll(pageRequest);
		Page<UserResponse> pageUserResponse = pageUsers.map(this::convert);
		return pageUserResponse;
	}
	
	private UserResponse convert(User user){
		return new UserResponse(user);
	}

	@Override
	public boolean delete(Integer id) {
		 userRepository.delete(id);
		 return true;
	}



	@Override
	public User findOne(Integer id) {
		return  userRepository.findOne(id);
	}


	@Override
	public List<User> findAll(SearchingRequest searchingRequest) {
		SearchingUser searchingUser = new SearchingUser(searchingRequest);
		return userRepository.findAll(searchingUser);
			}

	@Override
		public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByEmail(username);
	}
	
	  private boolean emailExist(final String email) {
	        return userRepository.findByEmail(email) != null;
	    }

	
	
}
