package seb.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Required;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

import seb.api.ValidationCodes;
import seb.api.bundle.BundleApplicable;
import seb.api.bundle.BundleDto;
import seb.api.bundle.BundleResolver;
import seb.api.bundle.BundleValidator;
import seb.api.dto.QuestionDto;
import seb.api.product.ProductApplicable;
import seb.api.product.ProductResolver;
import seb.domain.question.Question;

public class BundleValidatorImpl implements BundleValidator {

	private BundleResolver bundleResolver;
	private ProductResolver<ProductApplicable> productsResolver;
	
	@Override
	public Map<String, String> validate(BundleDto bundleDto) {

		String eligibleBundleBeanName = bundleDto.getBundleName(); 
		List<String> eligibleProductbeanNames = bundleDto.getProductNames();		
		Question q = bundleDto.getQuestion();			
		QuestionDto question = new QuestionDto.QuestionBuilder(q.getAge()).income(q.getIncome()).student(q.isStudent()).build();
		
		Map<String, String> result = new HashMap<>();
		
		result = validateBundleExists(result, eligibleBundleBeanName);
		if (!result.isEmpty() && result.containsKey(ValidationCodes.BUNDLE_NOT_FOND)) {
			return result;
		}		
		result = validateProductSupportedByBundle(eligibleBundleBeanName, eligibleProductbeanNames, result);		
		result = validateProductRules(question, result, findSupportedProducts(eligibleProductbeanNames, 
				bundleResolver.resolveByName(eligibleBundleBeanName)));
		
		// BUNDLE RULES...
		// ok. the architecture is not the one  we expected here.
		
		return result;
	}

	private Map<String, String> validateProductSupportedByBundle(String eligibleBundleBeanName, List<String> eligibleProductbeanNames,
			Map<String, String> result) {
		BundleApplicable bundle = bundleResolver.resolveByName(eligibleBundleBeanName);
		List<String> notSupportedProducts = findNotSupportedProducts(eligibleProductbeanNames, bundle);		
		if (!notSupportedProducts.isEmpty()) {
			result.put(ValidationCodes.PRODUCT_NOT_FOND, Joiner.on(", ").join(notSupportedProducts));
		}
		return result;
	}

	private List<String> findNotSupportedProducts(List<String> eligibleProductbeanNames, BundleApplicable bundle) {
		List<String> productBeanNames = bundle.resolveApplicableProducts();
		List<String> notSupportedProducts = Lists.newArrayList();
		for (String eligibleProduct : eligibleProductbeanNames) {
			if (!productBeanNames.contains(eligibleProduct)) {
				notSupportedProducts.add(eligibleProduct);
			}
		}
		return notSupportedProducts;
	}

	private List<String> findSupportedProducts(List<String> eligibleProductbeanNames, BundleApplicable bundle) {
		List<String> productBeanNames = bundle.resolveApplicableProducts();
		List<String> supportedProducts = Lists.newArrayList();
		for (String eligibleProduct : eligibleProductbeanNames) {
			if (productBeanNames.contains(eligibleProduct)) {
				supportedProducts.add(eligibleProduct);
			} 
		}
		return supportedProducts;
	}	
	
	private Map<String, String> validateProductRules(QuestionDto question, Map<String, String> result, List<String> supportedProducts) {
		List<String> productRulesFailed = Lists.newArrayList();
		for (String productBeanName : supportedProducts) {
			ProductApplicable product = productsResolver.resolveByName(productBeanName);
			boolean isApplicable = product.realize(question);
			if (!isApplicable) {
				productRulesFailed.add(productBeanName);
			}
		}
		if (!productRulesFailed.isEmpty()) {
			result.put(ValidationCodes.RULE_VIOLATION, Joiner.on(", ").join(productRulesFailed));			
		}
		return result;
	}

	Map<String, String> validateBundleExists(Map<String, String> result, String eligibleBundleBeanName) {
		BundleApplicable bundle = bundleResolver.resolveByName(eligibleBundleBeanName);
		if (bundle == null) {
			result.put(ValidationCodes.BUNDLE_NOT_FOND, eligibleBundleBeanName);
			return result;
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

	public ProductResolver<ProductApplicable> getProductsResolver() {
		return productsResolver;
	}

	@Required
	public void setProductsResolver(ProductResolver<ProductApplicable> productsResolver) {
		this.productsResolver = productsResolver;
	}



}
