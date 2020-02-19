package shopping.cart.api.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

/**
 * @author M1048474
 *
 */
@Component
public class Mappers {

	@OneToOne(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private User user = new User();

	@NotNull(message = "Product shoud not be null")
	@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Product> cartproducts = new ArrayList<Product>();

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Product> getCartproducts() {
		return cartproducts;
	}

	public void setCartproducts(List<Product> cartproducts) {
		this.cartproducts = cartproducts;
	}

}
