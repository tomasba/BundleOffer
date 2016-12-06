package seb.service.validator.product;

import java.util.List;

import seb.api.base.ValidationResultStatus;
import seb.api.customer.dto.Question;
import seb.domain.product.ProductRuleApplicable;

public interface ProductRuleValidator extends ProductValidator {
	List<ValidationResultStatus> validate(Question question, String productUid);
	List<ValidationResultStatus> validate(Question question, ProductRuleApplicable product);
}
