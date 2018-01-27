package ua.com.service;

import java.util.List;

import org.springframework.context.ApplicationListener;
import org.springframework.data.domain.Page;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;

import ua.com.entity.User;
import ua.com.request.LoginRequest;
import ua.com.request.MyPageRequest;
import ua.com.request.SearchingRequest;
import ua.com.response.UserResponse;
import ua.com.validation.EmailExistsException;

public interface UserService{

	User register(User user) throws EmailExistsException ;
	
	boolean login(LoginRequest loginRequest);
	
	Page<UserResponse> findAll(MyPageRequest page);
	
	boolean delete(Integer id);
	
	User findOne(Integer id);
	
	List<User> findAll(SearchingRequest searchingRequest);
}
