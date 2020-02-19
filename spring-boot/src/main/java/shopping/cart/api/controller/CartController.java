package shopping.cart.api.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import shopping.cart.api.model.Product;
import shopping.cart.api.repository.CartRepository;
import shopping.cart.api.service.CartService;



/**
 * @author M1048474
 *
 */
@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/cart/product")
public class CartController {
	
	private static final Logger logger = LoggerFactory.getLogger(CartController.class);

	/*
	 * User can view all the products present in cart
	 */

	@Autowired
	private CartService cartService;
	@Autowired
	private CartRepository cartRepository;

	@CrossOrigin("http://localhost:4200")
	@SuppressWarnings("unused")
	@GetMapping(value = "/getAll")
	public ResponseEntity<Map<String, Object>> getAllProduct() throws Exception {
		logger.debug("Fetching the record from the db");
		Map<String, Object> map = cartService.getAllProduct();
		logger.debug("Fetched all the records from the product db");
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.CREATED);

	}
	
	

}
