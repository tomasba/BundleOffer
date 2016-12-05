package seb.domain.product;

import java.util.Map;

import org.springframework.beans.factory.annotation.Required;

import seb.api.Displayable;
import seb.api.dto.QuestionDto;
import seb.api.product.AccountProductApplicable;
import seb.api.product.CardProductApplicable;
import seb.api.product.ProductResolver;

public class DebitCardProduct implements CardProductApplicable, Displayable {
	
	private ProductResolver<AccountProductApplicable> productsResolver;

	@Override
	public boolean realize(final QuestionDto question) {
		if (!isProductApplicabilitySatisfied(question)) {
			return false;
		}
		return true;
	}
	
	protected boolean isProductApplicabilitySatisfied(final QuestionDto question) {
		Map<String, AccountProductApplicable> accountProducts = productsResolver.resolve();
		for (AccountProductApplicable product : accountProducts.values()) {		
			if (product.realize(question)) {
				return true;
			}
		}
		return false;
	}	
	
	@Override
	public String getDisplayValue() {
		return "Debit Card";
	}

	public ProductResolver<AccountProductApplicable> getProductsResolver() {
		return productsResolver;
	}

	@Required
	public void setProductsResolver(ProductResolver<AccountProductApplicable> productsResolver) {
		this.productsResolver = productsResolver;
	}
	
}
