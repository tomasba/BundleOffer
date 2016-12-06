package seb.service.validator.product;

import java.util.List;

import seb.api.base.ValidationResultStatus;
import seb.api.customer.dto.Question;
import seb.domain.product.ProductCohesionApplicable;

public interface ProductCohesionValidator extends ProductRuleValidator {

	List<ValidationResultStatus> validate(Question question, ProductCohesionApplicable product);
}
