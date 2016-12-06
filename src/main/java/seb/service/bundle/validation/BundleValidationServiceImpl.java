package seb.service.bundle.validation;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Lists;

import seb.api.base.ValidationCodes;
import seb.api.base.ValidationResultStatus;
import seb.api.bundle.resolver.BundleResolver;
import seb.api.bundle.service.BundleValidationService;
import seb.api.customer.dto.Question;
import seb.domain.bundle.BundleApplicable;
import seb.service.validator.bundle.BundleRuleValidator;

public class BundleValidationServiceImpl implements BundleValidationService {

	private static final Logger log = LoggerFactory.getLogger(BundleValidationServiceImpl.class);
	
	private BundleResolver bundleResolver; 
	private BundleRuleValidator bundleRuleValidator;
	
	@Override
	public List<ValidationResultStatus> validateBundleAvailable(String bundleUid) {
		BundleApplicable bundle = bundleResolver.resolveByUid(bundleUid);		
		if (bundle == null) {
			ValidationResultStatus err = new ValidationResultStatus(ValidationCodes.BUNDLE_NOT_FOND, "Bundle with uid = " + bundleUid + " does not exist in repository.");
			return Lists.newArrayList(err);
		}
		return Lists.newArrayList();
	}

	@Override
	public List<ValidationResultStatus> validateProductSupportedByBundle(String productUid, String bundleUid) {
		return validateBundleContainsProduct(productUid, bundleUid);
	}
	
	@Override
	public List<ValidationResultStatus> validateProductSupportedByBundle(List<String> productUids, String bundleUid) {
		List<ValidationResultStatus> result = Lists.newArrayList();
		List<ValidationResultStatus> res = null;
		for (String uid : productUids) {
			res = validateBundleContainsProduct(uid, bundleUid);
			if (!res.isEmpty()) {
				result.addAll(res);
			}
		}
		return result;
	}	

	private List<ValidationResultStatus> validateBundleContainsProduct(String productUid, String bundleUid) {
		BundleApplicable bundle = bundleResolver.resolveByUid(bundleUid);
		if (bundle == null) {
			log.debug("Bundle with uid = {} does not exist. Product uid = {} validation for bundle uid = {} processed as failure.", bundleUid, productUid, bundleUid);
			ValidationResultStatus err = new ValidationResultStatus(ValidationCodes.UNSUPPORTED_BUNDLE_PRODUCT, "Bundle with uid = " + bundleUid + " does not exist and can not include product " + productUid);
			return Lists.newArrayList(err);			
		}
		List<String> bundleProductUids = bundle.resolveApplicableProducts();
		if (CollectionUtils.isEmpty(bundleProductUids)) {
			log.debug("Bundle uid = {} is not configured with applibale products. Product uid = {} support for bundle uid = {} marked as failed.", bundleUid, productUid, bundleUid);
			ValidationResultStatus err = new ValidationResultStatus(ValidationCodes.BUNDLE_CONFIG, "Bundle with uid = " + bundleUid + " is not configured with applicable products");
			return Lists.newArrayList(err);
		}
		if (!bundleProductUids.contains(productUid)) {
			ValidationResultStatus err = new ValidationResultStatus(ValidationCodes.PRODUCT_NOT_FOND, "Bundle with uid = " + bundleUid + " is not applicable with product uid = " + productUid);
			return Lists.newArrayList(err);			
		}
		return Lists.newArrayList();
	}	

	@Override
	public List<ValidationResultStatus> validateBundleWithRules(Question question, String bundleUid) {		
		return bundleRuleValidator.validate(question, bundleUid);
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

}
