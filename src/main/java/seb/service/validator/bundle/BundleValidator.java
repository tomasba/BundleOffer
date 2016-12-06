package seb.service.validator.bundle;

import java.util.List;

import seb.api.base.ValidationResultStatus;
import seb.api.customer.dto.Question;
import seb.domain.bundle.BundleApplicable;

public interface BundleValidator {

	List<ValidationResultStatus> validate(Question question, String bundleUid);
	List<ValidationResultStatus> validate(Question question, BundleApplicable bundle);		
	List<ValidationResultStatus> validate(Question question, List<BundleApplicable> bundles);
	
}
