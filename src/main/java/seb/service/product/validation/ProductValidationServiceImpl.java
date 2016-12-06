package seb.service.product.validation;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

import com.google.common.collect.Lists;

import seb.api.base.ValidationCodes;
import seb.api.base.ValidationResultStatus;
import seb.api.customer.dto.Question;
import seb.api.product.resolver.ProductResolver;
import seb.api.product.service.ProductValidationService;
import seb.domain.product.ProductApplicable;
import seb.domain.product.ProductCohesionApplicable;
import seb.domain.product.ProductRuleApplicable;
import seb.service.bundle.resolver.BundleResolverImpl;
import seb.service.validator.product.ProductCohesionValidator;
import seb.service.validator.product.ProductRuleValidator;

public class ProductValidationServiceImpl implements ProductValidationService {

	private static final Logger log = LoggerFactory.getLogger(BundleResolverImpl.class);
	
	private ProductCohesionValidator productCohesionValidator;
	
	private ProductRuleValidator productRuleValidator;

	private ProductResolver<ProductApplicable> productsResolver;
	
	@Override
	public List<ValidationResultStatus> validate(Question question, ProductApplicable product) {
		return validateProductApplicabilityForCustomer(question, product);
	}
	
	@Override
	public List<ValidationResultStatus> validate(Question question, List<ProductApplicable> product) {
		List<ValidationResultStatus> result = Lists.newArrayList();
		List<ValidationResultStatus> res;
		for (ProductApplicable p : product) {
			res = validateProductApplicabilityForCustomer(question, p);
			if (!res.isEmpty()) {
				result.addAll(res);
			}
		}
		return result;
	}
	
	@Override
	public List<ValidationResultStatus> validateProductWithRules(Question question, String productUid) {		
		return validateProductApplicabilityForCustomer(question, productsResolver.resolveByUid(productUid));
	}
	
	@Override
	public List<ValidationResultStatus> validateProductWithRules(Question question, List<String> productUids) {
		List<ValidationResultStatus> result = Lists.newArrayList();
		List<ValidationResultStatus> res;
		for (String uid : productUids) {
			res = validateProductApplicabilityForCustomer(question, productsResolver.resolveByUid(uid));
			if (!res.isEmpty()) {
				result.addAll(res);
			}
		}
		return result;
	}	
	
	private List<ValidationResultStatus> validateProductApplicabilityForCustomer(Question question, ProductApplicable product) {
		if (product == null) {
			return Lists.newArrayList(new ValidationResultStatus(ValidationCodes.PRODUCT_NOT_FOND, "Product provided could not be found to execute customer rules."));
		}
		if (product instanceof ProductRuleApplicable) {
			return productRuleValidator.validate(question, (ProductRuleApplicable)product);
		} 
		if (product instanceof ProductCohesionApplicable) {
			return productCohesionValidator.validate(question, (ProductCohesionApplicable)product);
		}
		log.warn("Product applicability was not processed for questionaire {}", question);
		return Lists.newArrayList();
	} 	
	
	public ProductCohesionValidator getProductCohesionValidator() {
		return productCohesionValidator;
	}	
	
	@Required
	public void setProductCohesionValidator(ProductCohesionValidator productCohesionValidator) {
		this.productCohesionValidator = productCohesionValidator;
	}

	public ProductRuleValidator getProductRuleValidator() {
		return productRuleValidator;
	}

	@Required
	public void setProductRuleValidator(ProductRuleValidator productRuleValidator) {
		this.productRuleValidator = productRuleValidator;
	}

	public ProductResolver<ProductApplicable> getProductsResolver() {
		return productsResolver;
	}

	@Required
	public void setProductsResolver(ProductResolver<ProductApplicable> productsResolver) {
		this.productsResolver = productsResolver;
	}

}
