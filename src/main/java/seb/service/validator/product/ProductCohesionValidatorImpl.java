package seb.service.validator.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Lists;

import seb.api.base.ValidationResultStatus;
import seb.api.customer.dto.Question;
import seb.api.product.resolver.ProductResolver;
import seb.api.rule.resolver.RulesResolver;
import seb.domain.product.ProductCohesionApplicable;
import seb.domain.product.ProductRuleApplicable;

public class ProductCohesionValidatorImpl implements ProductCohesionValidator {

	private RulesResolver rulesResolver;
	private ProductResolver<ProductRuleApplicable> productsResolver;	
	private ProductRuleValidator productRuleValidator;
	
	@Override
	public List<ValidationResultStatus> validate(Question question, String productUid) {
		ProductCohesionApplicable baseProduct = (ProductCohesionApplicable) productsResolver.resolveByUid(productUid);		
		return validateProductsCohession(question, baseProduct);
	}

	@Override
	public List<ValidationResultStatus> validate(Question question, ProductCohesionApplicable baseProduct) {
		return validateProductsCohession(question, baseProduct);
	}

	private List<ValidationResultStatus> validateProductsCohession(Question question, ProductCohesionApplicable baseProduct) {
		List<ValidationResultStatus> result = Lists.newArrayList();
		List<String> applicableProducts = baseProduct.resolveApplicableProducts();
		for (String productUid : applicableProducts) {
			ProductRuleApplicable product = (ProductRuleApplicable) productsResolver.resolveByUid(productUid);
			List<ValidationResultStatus> productRuleResult = productRuleValidator.validate(question, product);
			if (!CollectionUtils.isEmpty(productRuleResult)) {
				result.addAll(productRuleResult);
			}
		}
		return result;
	}

	public RulesResolver getRulesResolver() {
		return rulesResolver;
	}

	@Required
	public void setRulesResolver(RulesResolver rulesResolver) {
		this.rulesResolver = rulesResolver;
	}

	public ProductResolver<ProductRuleApplicable> getProductsResolver() {
		return productsResolver;
	}

	@Required
	public void setProductsResolver(ProductResolver<ProductRuleApplicable> productsResolver) {
		this.productsResolver = productsResolver;
	}

	public ProductRuleValidator getProductRuleValidator() {
		return productRuleValidator;
	}

	@Required
	public void setProductRuleValidator(ProductRuleValidator productRuleValidator) {
		this.productRuleValidator = productRuleValidator;
	}

	@Override
	public List<ValidationResultStatus> validate(Question question, ProductRuleApplicable product) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
