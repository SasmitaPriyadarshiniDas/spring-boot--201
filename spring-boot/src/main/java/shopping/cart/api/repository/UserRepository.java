package shopping.cart.api.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import shopping.cart.api.model.Cart;
import shopping.cart.api.model.User;

/**
 * @author M1048474
 *
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	@Query("SELECT u FROM User u where u.email = ?1")
	User findByEmail(String email);

	Cart save(Cart cart);

}
