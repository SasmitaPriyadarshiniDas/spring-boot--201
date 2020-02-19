
package shopping.cart.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.sun.istack.NotNull;

/**
 * @author M1048474
 *
 */
@Entity
public class Apparel extends Product{

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column
	@NotNull
	private String apparelType;
	@Column
	@NotNull
	private String apparelName;
	@Column
	@NotNull
	private String apparelBrand;

	public String getApparelType() {
		return apparelType;
	}

	public void setApparelType(String apparelType) {
		this.apparelType = apparelType;
	}

	public String getApparelName() {
		return apparelName;
	}

	public void setApparelName(String apparelName) {
		this.apparelName = apparelName;
	}

	public String getApparelBrand() {
		return apparelBrand;
	}

	public void setApparelBrand(String apparelBrand) {
		this.apparelBrand = apparelBrand;
	}

	
	

	@Override
	public String toString() {
		return "Apparel [apparelType=" + apparelType + ", apparelName=" + apparelName + ", apparelBrand=" + apparelBrand
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((apparelBrand == null) ? 0 : apparelBrand.hashCode());
		result = prime * result + ((apparelName == null) ? 0 : apparelName.hashCode());
		result = prime * result + ((apparelType == null) ? 0 : apparelType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Apparel other = (Apparel) obj;
		if (apparelBrand == null) {
			if (other.apparelBrand != null)
				return false;
		} else if (!apparelBrand.equals(other.apparelBrand))
			return false;
		if (apparelName == null) {
			if (other.apparelName != null)
				return false;
		} else if (!apparelName.equals(other.apparelName))
			return false;
		if (apparelType == null) {
			if (other.apparelType != null)
				return false;
		} else if (!apparelType.equals(other.apparelType))
			return false;
		return true;
	}


}
