package seb.api.bundle;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import seb.api.dto.QuestionDto;

public interface BundleApplicable extends BundleComparable {
	
	// TODO change API to return some ValitationResultStatus containing realization details, errors.
	// TODO probably it should be part of external service responsible for validation...
	boolean realize(final QuestionDto question);	
	boolean isContainingAccountProduct();
	@JsonProperty("Products")
	List<String> resolveApplicableProducts();
	@JsonProperty("Rules")
	List<String> resolveApplicableRules();	
	
}