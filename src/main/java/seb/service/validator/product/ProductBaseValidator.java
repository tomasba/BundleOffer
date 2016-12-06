package seb.service.validator.product;

import java.util.List;

import seb.api.base.ValidationResultStatus;
import seb.api.customer.dto.Question;
import seb.domain.product.ProductApplicable;

public interface ProductBaseValidator {

	List<ValidationResultStatus> validate(Question question, String productUid);
	<T extends ProductApplicable> List<ValidationResultStatus> validate(Question question, T validatorCriteria);
	
}
