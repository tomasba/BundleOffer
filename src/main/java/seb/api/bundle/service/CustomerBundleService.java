package seb.api.bundle.service;

import java.util.List;

import seb.api.customer.dto.Question;
import seb.domain.bundle.BundleApplicable;

public interface CustomerBundleService {

	List<BundleApplicable> resolveMostApplicableBundles(Question question);
	
	List<BundleApplicable> resolveApplicableBundles(Question question);
	
}