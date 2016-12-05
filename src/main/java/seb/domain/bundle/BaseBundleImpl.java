package seb.domain.bundle;

import org.springframework.beans.factory.annotation.Required;

import com.fasterxml.jackson.annotation.JsonIgnore;

import seb.api.bundle.BundleApplicable;
import seb.api.bundle.BundleComparable;
import seb.api.dto.QuestionDto;
import seb.api.product.AccountProductApplicable;
import seb.api.product.ProductApplicable;
import seb.api.product.ProductResolver;
import seb.api.rule.RuleApplicable;
import seb.api.rule.RulesResolver;

public abstract class BaseBundleImpl implements BundleApplicable {

	@JsonIgnore
	private ProductResolver<ProductApplicable> productsResolver;
	@JsonIgnore
	private RulesResolver rulesResolver;
	@JsonIgnore
	private boolean containsAccountProduct;
	
	
	@Override
	public boolean realize(final QuestionDto question) {
		if (!isBundleConfigured()) {
			return false;
		}
		if (!isRuleApplicabilitySatisfied(question)) {
			return false;
		}		
		return isProductApplicabilitySatisfied(question);
	}	
	
	@JsonIgnore
	@Override	
	public boolean isContainingAccountProduct() {
		return containsAccountProduct;
	}
	
	private boolean isBundleConfigured() {
		return !(resolveApplicableProducts() == null || resolveApplicableProducts().isEmpty());
	}

	protected boolean isProductApplicabilitySatisfied(final QuestionDto question) {
		for (String productBeanName : resolveApplicableProducts()) {
			ProductApplicable product = productsResolver.resolveByName(productBeanName);
			if (!product.realize(question)) {
				return false;
			}
			if (product instanceof AccountProductApplicable) {
				containsAccountProduct = true;
			}
		}		
		return true;
	}

	protected boolean isRuleApplicabilitySatisfied(final QuestionDto question) {
		for (String ruleBeanName : resolveApplicableRules()) {
			RuleApplicable rule = rulesResolver.resolveByName(ruleBeanName, question);
			if (!rule.realize()) {
				return false;
			}
		}
		return true;
	}	

	/**
	 * The highest priority will go first in ordered list
	 */
	@Override
	public int compareTo(BundleComparable bundle) {
		return bundle.resolvePriority() - this.resolvePriority();
	}	


	public RulesResolver getRulesResolver() {
		return rulesResolver;
	}

	@Required
	public void setRulesResolver(RulesResolver rulesResolver) {
		this.rulesResolver = rulesResolver;
	}

	public ProductResolver<ProductApplicable> getProductsResolver() {
		return productsResolver;
	}

	@Required
	public void setProductsResolver(ProductResolver<ProductApplicable> productsResolver) {
		this.productsResolver = productsResolver;
	}

}