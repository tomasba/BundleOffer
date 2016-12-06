package seb.service.validator.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Required;

import com.google.common.collect.Lists;

import seb.api.base.ValidationResultStatus;
import seb.api.customer.dto.Question;
import seb.api.product.resolver.ProductResolver;
import seb.api.rule.resolver.RulesResolver;
import seb.domain.product.ProductRuleApplicable;
import seb.domain.rule.RuleApplicable;

public class ProductRuleValidatorImpl implements ProductRuleValidator {

	private RulesResolver rulesResolver;
	private ProductResolver<ProductRuleApplicable> productsResolver;

	@Override
	public List<ValidationResultStatus> validate(Question question, String productUid) {
		ProductRuleApplicable product = productsResolver.resolveByUid(productUid);		
		return validateProductWithApplicableRules(question, product);
	}

	@Override
	public List<ValidationResultStatus> validate(Question question, ProductRuleApplicable product) {		
		return validateProductWithApplicableRules(question, product);
	}

	private List<ValidationResultStatus> validateProductWithApplicableRules(Question question, ProductRuleApplicable product) {
		List<ValidationResultStatus> result = Lists.newArrayList();
		List<String> aplicableRules = product.resolveApplicableRules();
		for (String ruleUid : aplicableRules) {
			RuleApplicable rule = rulesResolver.resolveByUid(ruleUid, question);
			if (!rule.realize()) {
				result.add(new ValidationResultStatus("ProductRule", "Rule id = " + ruleUid + " has failed for product "));
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
	
}
