package shopping.cart.api.service;

import shopping.cart.api.model.Apparel;
/**
 * @author M1048474
 *
 */
public interface ApparelService {

	Apparel addProduct(Apparel apparel) throws Exception;

	Apparel updateProduct(Apparel apparel, long product) throws Exception;

}
