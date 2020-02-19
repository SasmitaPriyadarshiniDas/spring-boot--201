package shopping.cart.api.service.Impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import shopping.cart.api.exception.RecordNotFoundException;
import shopping.cart.api.model.Cart;
import shopping.cart.api.model.User;
import shopping.cart.api.repository.CartRepository;
import shopping.cart.api.repository.UserRepository;
import shopping.cart.api.service.UserService;

/**
 * @author M1048474
 *
 */
@Service
public class UserServiceImpl implements UserService {
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRespository;

	@SuppressWarnings("unused")
	@Autowired
	private CartRepository cartRepository;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public User findOne(String email) {
		logger.debug("Finding an user by email");
		return userRespository.findByEmail(email);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public User save(User user) throws RecordNotFoundException {
		try {
			logger.debug("Adding new user in user database");
			user = userRespository.save(user);
			logger.debug("User is saved");
			return user;
		} catch (Exception e) {
			throw new RecordNotFoundException("Invalid user");
		}
	}

}
