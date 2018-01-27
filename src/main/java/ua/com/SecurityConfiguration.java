package ua.com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import ua.com.security.AuthenticationTokenFilter;
import ua.com.security.EntryPointUnauthorizedHandler;

@Configuration
@EnableWebSecurity
//Security filters with filter chain are configured and applied. @AuthenticationPrincipal annotation starts working. 
//ExceptionTranslationFilter catches AuthenticationExceptions and forwards to proper AuthorizationEntryPoints. 
//Basically, after this annotation alone our MVC services are not directly accessible anymore.
@EnableGlobalMethodSecurity(prePostEnabled = true)//PreAuthorize //allows AOP @PreAuthorize and some other annotations to be applied to methods
@ComponentScan("ua.com.*")
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{  //SecurityConfig extends WebSecurityConfigurerAdapter which allows to fine tune some configuration

	
	@Autowired 
	private EntryPointUnauthorizedHandler entryPointUnauthorizedHandler;
	
	@Autowired
	private UserDetailsService userDetails;

	@Autowired
	public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
		authenticationManagerBuilder.userDetailsService(this.userDetails).passwordEncoder(passwordEncoder());
		
		System.out.println("I am configuration method of user store in ss configuration");//Returns a DaoAuthenticationConfigurer to allow customization of the DAO authentication
	}
	
//	 @Override
//	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//	        auth
//	                .userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
//	    }
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.exceptionHandling()
		.authenticationEntryPoint(this.entryPointUnauthorizedHandler).and().sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
		.authorizeRequests() //There are multiple children to the http.authorizeRequests() method each matcher is considered in the order they were declared.
		.antMatchers("/**").permitAll() //	We specified URL patterns that any user can access. Specifically, any user can access a request if the URL starts with "/"
		.antMatchers("/auth/*").permitAll()
		.anyRequest().authenticated(); //Any URL that has not already been matched on only requires that the user be authenticated
		http.addFilterBefore(authenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
		System.out.println("I am onfigure (HttpSecurity http) methos");
	}
	
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception{
		System.out.println("I am authenticationManagerBean in ss configuration");
		return super.authenticationManagerBean();
	}
	
	@Bean
	public AuthenticationTokenFilter authenticationTokenFilter() throws Exception{
		AuthenticationTokenFilter authenticationTokenFilter = new AuthenticationTokenFilter();
		authenticationTokenFilter.setAuthenticationManager(authenticationManagerBean());
		
		System.out.println("I am authenticationTokenFilter() method in ss configuration");
		return authenticationTokenFilter;
	}
	@Bean
	public PasswordEncoder passwordEncoder(){
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
	
		
}
