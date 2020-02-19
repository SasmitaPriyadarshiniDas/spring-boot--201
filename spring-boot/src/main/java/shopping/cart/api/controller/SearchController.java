package shopping.cart.api.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import shopping.cart.api.model.Product;
import shopping.cart.api.repository.SearchRespository;
import shopping.cart.api.service.SearchService;

/**
 * @author M1048474
 *
 */
/* Search product by id, name and category */

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping(value = "search/api")
public class SearchController {
	
	private static final Logger logger = LoggerFactory.getLogger(SearchController.class);

	@Autowired
	private SearchService searchService;

	@Autowired
	private SearchRespository searchRespository;

	/* Search product by id */
	@GetMapping("productById/{productId}")
	public ResponseEntity<Product> searchProductById(@PathVariable(value = "productId") Long productId)
			throws Exception {
		logger.debug("Calling the service to search the product");
		Product product = searchService.searchProductById(productId);
		logger.debug("Product is searched by id");
		return new ResponseEntity<Product>(product, HttpStatus.OK);

	}

	/* Search product by name */
	@GetMapping("productByName/{productName}")
	public ResponseEntity<List<Product>> searchProductByName(@PathVariable(value = "productName") String productName)
			throws Exception {
		logger.debug("Calling the service to search the product");
		List<Product> product = searchService.searchProductByname(productName);
		logger.debug("Product is searched by item name");
		return new ResponseEntity<List<Product>>(product, HttpStatus.OK);

	}

	/* Search product by category */
	@GetMapping("productByCategory/{category}")
	public ResponseEntity<List<Product>> searchProductByCategory(@PathVariable(value = "category") String category)
			throws Exception {
		logger.debug("Calling the service to search the product");
		List<Product> product = searchService.searchProductByCategory(category);
		logger.debug("Product is searched by category");
		return new ResponseEntity<List<Product>>(product, HttpStatus.OK);

	}

}
