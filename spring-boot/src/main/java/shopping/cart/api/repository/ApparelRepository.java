package shopping.cart.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import shopping.cart.api.model.Apparel;
import shopping.cart.api.model.Cart;
import shopping.cart.api.model.User;
/**
 * @author M1048474
 *
 */
@Repository
public interface ApparelRepository extends JpaRepository<Apparel, Long>{
	
}

	


