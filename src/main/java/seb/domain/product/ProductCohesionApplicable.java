package seb.domain.product;

import java.util.List;

public interface ProductCohesionApplicable extends ProductApplicable {

	List<String> resolveApplicableProducts();
	
}
