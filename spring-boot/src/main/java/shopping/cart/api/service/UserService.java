package shopping.cart.api.service;

import shopping.cart.api.exception.RecordNotFoundException;
import shopping.cart.api.model.User;
/**
 * @author M1048474
 *
 */
public interface UserService {
	
	User findOne(String email);
	User save(User user) throws RecordNotFoundException;

}
