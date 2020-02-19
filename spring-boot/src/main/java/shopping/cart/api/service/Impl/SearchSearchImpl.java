/**
 * 
 */
package shopping.cart.api.service.Impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import shopping.cart.api.exception.RecordNotFoundException;
import shopping.cart.api.model.Product;
import shopping.cart.api.repository.SearchRespository;
import shopping.cart.api.service.SearchService;

/**
 * @author M1048474
 *
 */
@Service
public class SearchSearchImpl implements SearchService {
	private static final Logger logger = LoggerFactory.getLogger(SearchSearchImpl.class);

	@Autowired
	private SearchRespository searchRespository;

	/* Search product by id */

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public Product searchProductById(long productId) throws Exception {

		Optional<Product> optionalProduct = searchRespository.findById(productId);
		Product newProduct = optionalProduct.get();
		logger.debug("fetched an item from db by using Id");
		try {
			if (!optionalProduct.isPresent()) {
				logger.debug("validating an item is present or not");
				throw new RecordNotFoundException("Product does not exists in cart");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newProduct;
	}

	/* Search product by name */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Product> searchProductByname(String productName) throws Exception {
		logger.debug("Calling the repository to get an item ");
		List<Product> product = searchRespository.findByName(productName);
		logger.debug("fetched an item from db by using product name");

		try {
			if (product == null) {
				logger.debug("validating an item is present or not");
				throw new RecordNotFoundException("Product does not exists in cart");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return product;
	}

	/* Search product by category */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Product> searchProductByCategory(String category) throws Exception {
		logger.debug("Calling the repository to get an item ");
		List<Product> product = searchRespository.findByCategory(category);
		logger.debug("fetched an item from db by using category");
		try {
			if (product == null) {
				logger.debug("validating an item is present or not");
				throw new RecordNotFoundException("Product does not exists in cart");
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return product;
	}

}
