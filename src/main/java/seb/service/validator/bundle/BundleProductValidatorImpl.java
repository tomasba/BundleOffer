package seb.service.validator.bundle;

import java.util.List;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Lists;

import seb.api.base.ValidationResultStatus;
import seb.api.bundle.resolver.BundleResolver;
import seb.api.customer.dto.Question;
import seb.api.product.resolver.ProductResolver;
import seb.domain.bundle.BundleApplicable;
import seb.domain.product.ProductCohesionApplicable;
import seb.domain.product.ProductRuleApplicable;
import seb.service.validator.product.ProductRuleValidator;

public class BundleProductValidatorImpl<T> implements BundleProductValidator {

	private BundleResolver bundleResolver;
	
	private ProductResolver<T> productResolver;
		
	private ProductRuleValidator productRuleValidator;
	
	private ProductRuleValidator productCohesionValidator;
	
	@Override
	public List<ValidationResultStatus> validate(Question question, String bundleUid) {
		BundleApplicable bundle = bundleResolver.resolveByUid(bundleUid);
		return validateBundleProducts(question, bundle);
	}

	@Override
	public List<ValidationResultStatus> validate(Question question, BundleApplicable bundle) {
		return validateBundleProducts(question, bundle);
	}
	
	@Override
	public List<ValidationResultStatus> validate(Question question, List<BundleApplicable> bundles) {
		List<ValidationResultStatus> result = Lists.newArrayList();				
		for (BundleApplicable bundle : bundles) {
			List<ValidationResultStatus> bundleValidationResult = validateBundleProducts(question, bundle);
			if (!CollectionUtils.isEmpty(bundleValidationResult)) {
				result.addAll(bundleValidationResult);
			}
		}
		return result;
	}

	private List<ValidationResultStatus> validateBundleProducts(Question question, BundleApplicable bundle) {
		List<ValidationResultStatus> result = Lists.newArrayList();
		List<String> applicableBundleProducts = bundle.resolveApplicableProducts();
		List<ValidationResultStatus> productRuleResult;
		for (String productUid : applicableBundleProducts) {
			ProductRuleApplicable product = (ProductRuleApplicable) productResolver.resolveByUid(productUid);
			if (product instanceof ProductCohesionApplicable) {
				productRuleResult = productCohesionValidator.validate(question, product);
			} else {
				productRuleResult = productRuleValidator.validate(question, product);
			}
			if (!CollectionUtils.isEmpty(productRuleResult)) {
				result.addAll(productRuleResult);
			}
		}
		return result;
	}

	public BundleResolver getBundleResolver() {
		return bundleResolver;
	}

	@Required
	public void setBundleResolver(BundleResolver bundleResolver) {
		this.bundleResolver = bundleResolver;
	}

	public ProductResolver<T> getProductResolver() {
		return productResolver;
	}

	@Required
	public void setProductResolver(ProductResolver<T> productResolver) {
		this.productResolver = productResolver;
	}

	public ProductRuleValidator getProductRuleValidator() {
		return productRuleValidator;
	}
	
	@Required
	public void setProductRuleValidator(ProductRuleValidator productRuleValidator) {
		this.productRuleValidator = productRuleValidator;
	}

	public ProductRuleValidator getProductCohesionValidator() {
		return productCohesionValidator;
	}

	@Required
	public void setProductCohesionValidator(ProductRuleValidator productCohesionValidator) {
		this.productCohesionValidator = productCohesionValidator;
	}

}
