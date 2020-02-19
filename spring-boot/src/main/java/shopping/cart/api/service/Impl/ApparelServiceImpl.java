package shopping.cart.api.service.Impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import shopping.cart.api.controller.ApparelController;
import shopping.cart.api.exception.RecordNotFoundException;
import shopping.cart.api.model.Apparel;
import shopping.cart.api.repository.ApparelRepository;
import shopping.cart.api.service.ApparelService;

/**
 * @author M1048474
 *
 */
@Service
public class ApparelServiceImpl implements ApparelService {

	private static final Logger logger = LoggerFactory.getLogger(ApparelServiceImpl.class);
	@Autowired
	ApparelRepository apparelRepository;

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Apparel addProduct(Apparel apparel) throws Exception {

		Apparel newApparel = apparel;
		logger.debug("Adding item into Productdb");
		try {

			newApparel.setQuantity(apparel.getQuantity());

			if (newApparel.getQuantity() > 1) {
				logger.debug("Setting the price based on number of items");
				newApparel.setPrice(apparel.getPrice() * apparel.getQuantity());
				logger.debug("Price set for items");
			}
			logger.debug("Calling the repository to save an item in db");
			apparel = apparelRepository.save(apparel);
			logger.debug("item is saved in db");

			if (apparel == null) {
				throw new RecordNotFoundException("Product does not exists in the cart");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return apparel;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Apparel updateProduct(Apparel apparel, long productId) throws Exception {


		Optional<Apparel> optionalProduct = apparelRepository.findById(productId);
		logger.debug("calling the repository to get item from db by id");
		Apparel newApparel = optionalProduct.get();
		try {
			if (optionalProduct.isPresent()) {
				logger.debug("Validating an item is present or not");
				newApparel.setQuantity(apparel.getQuantity());
				if (newApparel.getQuantity() > 1) {
					logger.debug("Setting the price based on number of items");
					newApparel.setPrice(apparel.getPrice() * apparel.getQuantity());
					logger.debug("Price is set");
					System.out.println(apparel.getPrice() * apparel.getQuantity());
				}

				else {
					throw new RecordNotFoundException("Product does not exists in the cart");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newApparel;
	}

}
