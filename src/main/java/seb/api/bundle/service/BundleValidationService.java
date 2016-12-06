package seb.api.bundle.service;

import java.util.List;

import seb.api.base.ValidationResultStatus;
import seb.api.customer.dto.Question;

public interface BundleValidationService {

	List<ValidationResultStatus> validateBundleAvailable(String bundleUid);
	
	List<ValidationResultStatus> validateProductSupportedByBundle(String productUid, String bundleUid);
	
	List<ValidationResultStatus> validateProductSupportedByBundle(List<String> productUid, String bundleUid);
	
	List<ValidationResultStatus> validateBundleWithRules(Question question, String bundleUid);
	
}
