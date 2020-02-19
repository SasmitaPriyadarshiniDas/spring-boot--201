package shopping.cart.api.service.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import shopping.cart.api.exception.RecordNotFoundException;
import shopping.cart.api.model.Product;
import shopping.cart.api.repository.CartRepository;
import shopping.cart.api.service.CartService;

/**
 * @author M1048474
 *
 */
@Service
public class CartServiceImpl implements CartService {
	private static final Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);

	@Autowired
	CartRepository cartRepository;

	@SuppressWarnings("unused")
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Map<String, Object> getAllProduct() throws Exception {
		logger.debug("Calling the repository to get all cart items");
		List<Product> productlist = cartRepository.findAll();

		Map<String, Object> message = new HashMap<String, Object>();
		Float price[] = new Float[productlist.size()];
		logger.debug("Getting the size of cart");
		ArrayList<Float> totalprice = new ArrayList<Float>();
		ArrayList<Integer> totlaItems = new ArrayList<Integer>();
		try {
			int i = 0;
			Float totalsum = 0f;
			for (Product p : productlist) {

				price[i] = p.getPrice() * p.getQuantity();
				logger.debug("Getting the price of each item and stored in price array");
				i++;
			}
			for (int j = 0; j < price.length; j++) {

				totalsum += price[j];
				logger.debug("calculating total price of cart");
			}
			logger.debug("Add cart price in  map object");
			totalprice.add(totalsum);
			// totalprice.add((double) productlist.size());
			logger.debug("Add cart size in  map object");
			totlaItems.add(productlist.size());
			logger.debug("Add cart items in map object");
			message.put("ListOFProductDetails", productlist);
			message.put("TotalNumberOfItemsInACart", totalprice);
			message.put("TotalCartPrice", totlaItems);
			/*
			 * message.put("list2", totalprice); message.put("list3", totlaItems);
			 */

			/*
			 * Map<String, Object> json = new HashMap<String, Object>(); json.put("Success",
			 * true); json.put("message", message);
			 */

			if (message == null) {
				throw new RecordNotFoundException("Cart is empty");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return message;
	}

}
