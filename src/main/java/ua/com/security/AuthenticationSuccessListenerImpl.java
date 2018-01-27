package ua.com.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import ua.com.entity.User;
import ua.com.repository.UserRepository;

@Component
public class AuthenticationSuccessListenerImpl implements ApplicationListener<AuthenticationSuccessEvent> {

	@Autowired
	private UserRepository userRepository;
	@Override
	public void onApplicationEvent(AuthenticationSuccessEvent event) {
		User user = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		user.setLastActive(new Date());
		userRepository.save(user);
		System.out.println(user.getLastActive());
		System.out.println(event.getAuthentication());
		System.out.println("I have logged in!");
	}
}
