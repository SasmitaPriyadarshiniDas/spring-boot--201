package shopping.cart.api.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import shopping.cart.api.exception.RecordNotFoundException;
import shopping.cart.api.model.Apparel;
import shopping.cart.api.repository.ApparelRepository;
import shopping.cart.api.service.ApparelService;

//@CrossOrigin("/**")

/**
 * @author M1048474
 *
 */
@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("apparel/api")
public class ApparelController {
	
	private static final Logger logger = LoggerFactory.getLogger(ApparelController.class);

	@Autowired
	ApparelService apparelService;
	@Autowired
	ApparelRepository apparelRepository;

	@PostMapping("/save")
	public ResponseEntity<Apparel> addProduct(@RequestBody Apparel apparel) throws Exception {
		logger.debug("Calling the service to add the product");
		apparel = apparelService.addProduct(apparel);
		logger.debug("Apparel is saved in db");
		return new ResponseEntity<Apparel>(apparel, HttpStatus.ACCEPTED);

	}

	@PutMapping(value = "/update/{productId}")
	public ResponseEntity<Apparel> updateProductQuantity(@RequestBody Apparel apparel,

			@PathVariable("productId") long productId) throws Exception {
		logger.debug("Calling the service to update the Apparel");
		apparel = apparelService.updateProduct(apparel, productId);
		logger.debug("Apparel is saved in db");
		return new ResponseEntity<Apparel>(apparel, HttpStatus.OK);

	}

	@DeleteMapping(value = "/delete/{productId}")
	public void deleteProduct(@PathVariable(value = "productId") Long productId) throws Exception {
		logger.debug("Fetching the record from the db");
		Optional<Apparel> optionalBook = apparelRepository.findById(productId);
		logger.debug("Validating the product");
		if (optionalBook.isPresent()) {
			logger.debug("Deleting the product from db");
			apparelRepository.deleteById(productId);
			logger.debug("Deleted the product from db");
		} else {
			logger.debug("Product is not found");
			throw new RecordNotFoundException("No record exist for the given id");
		}

	}

}
