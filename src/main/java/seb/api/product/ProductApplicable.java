package seb.api.product;

import seb.api.dto.QuestionDto;

public interface ProductApplicable {
	// TODO change API to return some ValitationResultStatus containing realization details, errors.
	// TODO probably it should be part of external service responsible for validation...
	boolean realize(QuestionDto question);
	
}