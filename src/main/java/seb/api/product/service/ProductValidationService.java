package seb.api.product.service;

import java.util.List;

import seb.api.base.ValidationResultStatus;
import seb.api.customer.dto.Question;
import seb.domain.product.ProductApplicable;

public interface ProductValidationService {

	List<ValidationResultStatus> validate(Question question, ProductApplicable product);
	
	List<ValidationResultStatus> validate(Question question, List<ProductApplicable> product);
	
	List<ValidationResultStatus> validateProductWithRules(Question question, String productUid);
	
	List<ValidationResultStatus> validateProductWithRules(Question question, List<String> productUid);	
	
}
