package ua.com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@Configuration //tells Spring that this class will act as a configuration source. There can be many such classes
@EnableAutoConfiguration //tells Spring-Boot to try to autoconfigure itself by using default values. Any our custom parts replace the defaults
@SpringBootApplication 
@EnableJpaRepositories
public class SimpleRestServerPartApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleRestServerPartApplication.class, args);
		
	}

}
