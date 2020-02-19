package shopping.cart.api.service.Impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import shopping.cart.api.exception.RecordNotFoundException;
import shopping.cart.api.model.Book;
import shopping.cart.api.repository.BookRepository;
import shopping.cart.api.service.BookService;

/**
 * @author M1048474
 *
 */
@Service
public class BookServiceImpl implements BookService {

	private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

	@Autowired
	BookRepository bookRepository;

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public Book addProduct(Book book) throws Exception {

		Book newBook = book;
		newBook.setQuantity(book.getQuantity());
		logger.debug("Adding item into Productdb");
		try {
			if (newBook.getQuantity() > 1) {
				logger.debug("Setting the price based on number of items");
				newBook.setPrice(book.getPrice() * book.getQuantity());
				logger.debug("Price set for items");
			}
			logger.debug("Calling the repository to save an item in db");
			book = bookRepository.save(book);
			logger.debug("item is saved in db");

			if (book == null) {

				throw new RecordNotFoundException("Product does not exists in cart");
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		return book;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Book updateProduct(Book book, long productId) throws Exception {

		Optional<Book> optionalProduct = bookRepository.findById(productId);
		logger.debug("calling the repository to get item from db by id");
		Book newBook = optionalProduct.get();

		try {
			if (optionalProduct.isPresent()) {
				logger.debug("Validating an item is present or not");
				newBook.setQuantity(book.getQuantity());

				if (newBook.getQuantity() > 1) {
					logger.debug("Setting the price based on number of items");
					newBook.setPrice(book.getPrice() * book.getQuantity());
					logger.debug("Price is set");
					System.out.println(book.getPrice() * book.getQuantity());
				}
			} else {

				throw new RecordNotFoundException("Product does not exists in cart");
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		return newBook;

	}

}
