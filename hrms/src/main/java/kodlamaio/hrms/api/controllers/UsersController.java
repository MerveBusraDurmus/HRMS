package kodlamaio.hrms.api.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.UserService;
import kodlamaio.hrms.core.entities.User;
import kodlamaio.hrms.core.utilities.results.DataResult;

@RestController
@RequestMapping("/api/users")
public class UsersController {

	private UserService userService;

	public UsersController(UserService userService) {
		this.userService = userService;
	}
	
	
	@GetMapping("/getbyemail")
	
	public DataResult<User> getByEmail(String email){
		return this.userService.getByEmail(email);
	}
}
