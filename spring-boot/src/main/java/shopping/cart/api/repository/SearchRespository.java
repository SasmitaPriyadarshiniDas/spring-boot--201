package shopping.cart.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import shopping.cart.api.model.Product;

/**
 * @author M1048474
 *
 */
@Repository
public interface SearchRespository extends JpaRepository<Product, Long> {

	@Query("SELECT p FROM Product p where p.productName = ?1")
	public List<Product> findByName(String productName);

	@Query("SELECT p FROM Product p where p.category = ?1")
	public List<Product> findByCategory(String category);

}
