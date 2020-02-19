package shopping.cart.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import shopping.cart.api.model.Cart;

/**
 * @author M1048474
 *
 */

@Repository
public interface UserDetailsRepository extends JpaRepository<Cart, Long> {

}
