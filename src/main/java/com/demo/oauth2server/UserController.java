package com.demo.oauth2server;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	private static final Log logger = LogFactory.getLog(UserController.class);

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/users")
	@ResponseStatus(HttpStatus.OK)
	public List<UserModel> listAllUsers() {
		List<User> users = userRepository.findAll();
		List<UserModel> filteredUsers = users.stream().map(u -> {
			UserModel user = new UserModel();
			user.setUsername(u.getUsername());
			user.setEmail(u.getEmail());
			user.setRole(u.getAuthorities().stream().findFirst().get().getAuthority());
			return user;
		}).collect(Collectors.toList());
		return filteredUsers;
	}
	
	@PostMapping("/user")
	@ResponseStatus(HttpStatus.OK)
	public void createUser(@RequestBody User user) {
		userRepository.save(user);
	}

}
