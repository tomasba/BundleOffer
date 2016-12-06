package seb.service.validator.bundle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Lists;

import seb.api.base.Displayable;
import seb.api.base.ValidationCodes;
import seb.api.base.ValidationResultStatus;
import seb.api.bundle.resolver.BundleResolver;
import seb.api.customer.dto.Question;
import seb.api.rule.resolver.RulesResolver;
import seb.domain.bundle.BundleApplicable;
import seb.domain.rule.RuleApplicable;

public class BundleRuleValidatorImpl implements BundleRuleValidator {

	@Autowired(required=true)
	private BundleResolver bundleResolver;
	
	@Autowired(required=true)
	private RulesResolver rulesResolver;
	
	@Override
	public List<ValidationResultStatus> validate(Question question, String bundleUid) {		
		BundleApplicable bundle = bundleResolver.resolveByUid(bundleUid);		
		return validateBundleWithApplicableRules(question, bundle);
	}

	@Override
	public List<ValidationResultStatus> validate(Question question, BundleApplicable bundle) {		
		return validateBundleWithApplicableRules(question, bundle);
	}	
	
	@Override
	public List<ValidationResultStatus> validate(Question question, List<BundleApplicable> bundles) {
		List<ValidationResultStatus> result = Lists.newArrayList();
		for (BundleApplicable bundle : bundles) {
			List<ValidationResultStatus> bundleValidationResult = validateBundleWithApplicableRules(question, bundle);
			if (!CollectionUtils.isEmpty(bundleValidationResult)) {
				result.addAll(bundleValidationResult);
			}
		}
		return result;
	}	
	
	private List<ValidationResultStatus> validateBundleWithApplicableRules(Question question, BundleApplicable bundle) {
		List<ValidationResultStatus> result = Lists.newArrayList();
		if (bundle == null) {
			result.add(new ValidationResultStatus(ValidationCodes.BUNDLE_NOT_FOND, "Could not locate bundle in the repository. Can not process with bundle rules evaluation."));
			return result;
		}		
		for (String ruleUid : bundle.resolveApplicableRules()) {
			RuleApplicable rule = rulesResolver.resolveByUid(ruleUid, question);
			if (!rule.realize()) {
				result.add(new ValidationResultStatus("BundleRule", "Bundle rule " + rule +" validation failed with question " + question + " for bundle " + ((Displayable)bundle).getDisplayValue()));
			}			
		}
		return result;
	}

	public BundleResolver getBundleResolver() {
		return bundleResolver;
	}

	public void setBundleResolver(BundleResolver bundleResolver) {
		this.bundleResolver = bundleResolver;
	}

	public RulesResolver getRulesResolver() {
		return rulesResolver;
	}

	public void setRulesResolver(RulesResolver rulesResolver) {
		this.rulesResolver = rulesResolver;
	}

}
