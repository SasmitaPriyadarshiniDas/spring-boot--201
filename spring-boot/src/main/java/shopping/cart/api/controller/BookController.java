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
import shopping.cart.api.model.Book;
import shopping.cart.api.repository.BookRepository;
import shopping.cart.api.service.BookService;

/**
 * @author M1048474
 *
 */
@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/book/api")
public class BookController {

	private static final Logger logger = LoggerFactory.getLogger(BookController.class);

	@Autowired
	private BookService bookService;

	@SuppressWarnings("unused")
	@Autowired
	private BookRepository bookRepository;

	@PostMapping("/save")
	public ResponseEntity<Book> addProduct(@RequestBody Book book) throws Exception {
		logger.debug("Calling the service to add the product");
		book = bookService.addProduct(book);
		logger.debug("Apparel is saved in db");
		return new ResponseEntity<Book>(book, HttpStatus.ACCEPTED);

	}

	@SuppressWarnings("unused")

	@PutMapping(value = "/update/{productId}")
	public ResponseEntity<Book> updateProductQuantity(@RequestBody Book book, @PathVariable("productId") long productId)
			throws Exception {
		logger.debug("Calling the service to update the Apparel");
		book = bookService.updateProduct(book, productId);
		logger.debug("Apparel is saved in db");
		return new ResponseEntity<Book>(book, HttpStatus.OK);

	}

	@DeleteMapping(value = "/delete/{productId}")
	public void deleteProduct(@PathVariable(value = "productId") Long productId) throws Exception {
		logger.debug("Fetching the record from the db");
		Optional<Book> optionalBook = bookRepository.findById(productId);
		logger.debug("Validating the product");
		if (optionalBook.isPresent()) {
			logger.debug("Deleting the product from db");
			bookRepository.deleteById(productId);
			logger.debug("Deleted the product from db");
		} else {
			logger.debug("Product is not found");
			throw new RecordNotFoundException("No record exist for given id");
		}

	}

}
