package shopping.cart.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import shopping.cart.api.model.Cart;
import shopping.cart.api.model.User;
import shopping.cart.api.repository.CartRepository;
import shopping.cart.api.repository.UserDetailsRepository;
import shopping.cart.api.repository.UserRepository;
import shopping.cart.api.service.UserService;

/**
 * @author M1048474
 *
 */
@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping(value = "/user")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	UserDetailsRepository userDetailsRepository;

	@PostMapping("/login/{email}")
	public ResponseEntity<User> login(@PathVariable("email") String email) {

		try {
			logger.debug("Calling the service to get a user");
			User user = userService.findOne(email);
			logger.debug("User is found successfully");
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<User>(HttpStatus.UNAUTHORIZED);
		}
	}

	@PostMapping("/register")
	public ResponseEntity<User> save(@RequestBody User user) {
		Cart cart = new Cart();
		cart.getCartId();
		logger.debug("Calling the service to register the user");
		userDetailsRepository.save(cart);
		logger.debug("Generating card id per user");
		user.setCart(cart);
		try {
			logger.debug("User register successfully");
			return ResponseEntity.ok(userService.save(user));

		} catch (Exception e) {
			logger.debug("Invalid data");
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping("/profile/{email}")
	public ResponseEntity<User> getProfile(@PathVariable("email") String email) {
		logger.debug("Calling the service to get the user profile");
		User user = userService.findOne(email);
		logger.debug("User profile is found");
		if (user.getEmail().equals(email)) {
			logger.debug("validating the user");
			return ResponseEntity.ok(user);
		} else {
			logger.debug("User is not found");
			return ResponseEntity.badRequest().build();
		}

	}

}
