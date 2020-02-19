package shopping.cart.api.service;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import shopping.cart.api.model.Product;

/**
 * @author M1048474
 *
 */
public interface SearchService {

	public Product searchProductById(long productId) throws Exception;
	
	public List<Product> searchProductByname(String productName)throws Exception;
	
	List<Product> searchProductByCategory(String category)throws Exception;

}
