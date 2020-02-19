package shopping.cart.api.service;

import shopping.cart.api.model.Book;
/**
 * @author M1048474
 *
 */
public interface BookService {

	Book addProduct(Book book) throws Exception;


	Book updateProduct(Book book, long productId) throws Exception;

}
