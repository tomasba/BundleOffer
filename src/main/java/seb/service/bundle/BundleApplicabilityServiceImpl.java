package seb.service.bundle;

import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

import seb.api.base.Displayable;
import seb.api.base.ValidationResultStatus;
import seb.api.bundle.resolver.BundleResolver;
import seb.api.bundle.service.BundleApplicabilityService;
import seb.api.customer.dto.Question;
import seb.domain.bundle.BundleApplicable;
import seb.service.validator.bundle.BundleProductValidator;
import seb.service.validator.bundle.BundleRuleValidator;

public class BundleApplicabilityServiceImpl implements BundleApplicabilityService {

	private static final Logger log = LoggerFactory.getLogger(BundleApplicabilityServiceImpl.class);
	
	private BundleResolver bundleResolver;
	private BundleRuleValidator bundleRuleValidator;
	private BundleProductValidator bundleProductValidator;
	
	@Override
	public PriorityQueue<BundleApplicable> retrieveOrderedBundles(Question question) {
		Map<String, BundleApplicable> bundles = bundleResolver.resolve();
		PriorityQueue<BundleApplicable> pq = new PriorityQueue<>();
		
		for (BundleApplicable bundle : bundles.values()) {
			if (isBundleRulesValid(bundle, question) && isBundleProductRulesValid(bundle, question)) {
				pq.add(bundle);
				log.info(((Displayable)bundle).getDisplayValue() + " - has been added.");
			} else {
				log.info(((Displayable)bundle).getDisplayValue() + " - has not been added. Applicability rules are not satisfied.");
			}
		}
		return pq;		
	}
	
	private boolean isBundleProductRulesValid(BundleApplicable bundle, Question question) {
		List<ValidationResultStatus> result = bundleProductValidator.validate(question, bundle);
		return result.isEmpty();
	}

	private boolean isBundleRulesValid(BundleApplicable bundle, Question question) {
		List<ValidationResultStatus> result = bundleRuleValidator.validate(question, bundle);
		return result.isEmpty();
	}

	public BundleResolver getBundleResolver() {
		return bundleResolver;
	}

	@Required
	public void setBundleResolver(BundleResolver bundleResolver) {
		this.bundleResolver = bundleResolver;
	}

	public BundleRuleValidator getBundleRuleValidator() {
		return bundleRuleValidator;
	}

	@Required
	public void setBundleRuleValidator(BundleRuleValidator bundleRuleValidator) {
		this.bundleRuleValidator = bundleRuleValidator;
	}

	public BundleProductValidator getBundleProductValidator() {
		return bundleProductValidator;
	}

	@Required
	public void setBundleProductValidator(BundleProductValidator bundleProductValidator) {
		this.bundleProductValidator = bundleProductValidator;
	}	
}
