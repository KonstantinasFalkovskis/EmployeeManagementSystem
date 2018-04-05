package com.example.demo;

import com.example.demo.entities.User;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		User admin = new User("admin@mail.com", "Admin", "123456");
//		userService.createAdmin(admin);
//
//		User user = new User("info@gmail.com", "info", "123456");
//		userService.createUser(user);
//		User user1 = new User("info@gmail.com", "info", "123456");
//		userService.createUser(user1);
//		User user2 = new User("info@gmail.com", "info", "123456");
//		userService.createUser(user2);
//		User user3 = new User("info@gmail.com", "info", "123456");
//		userService.createUser(user3);
//		User user4 = new User("info@gmail.com", "info", "123456");
//		userService.createUser(user4);
//		User user5 = new User("info@gmail.com", "info", "123456");
//		userService.createUser(user5);
//		User user6 = new User("info@gmail.com", "info", "123456");
//		userService.createUser(user6);
//		User user7 = new User("info@gmail.com", "info", "123456");
//		userService.createUser(user7);
//		User user8 = new User("info@gmail.com", "info", "123456");
//		userService.createUser(user8);
//		User user9 = new User("info@gmail.com", "info", "123456");
//		userService.createUser(user9);
	}
}
