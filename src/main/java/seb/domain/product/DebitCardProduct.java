package seb.domain.product;

import java.util.List;

import seb.api.base.Displayable;

public class DebitCardProduct implements ProductCohesionApplicable, ProductRuleApplicable, CardProductApplicable, Displayable {	

	private List<String> applicableProducts;
	
	public DebitCardProduct(List<String> products) {
		this.applicableProducts = products;
	}
	
//	@Override
//	public boolean realize(final Question question) {
//		if (!isProductApplicabilitySatisfied(question)) {
//			return false;
//		}
//		return true;
//	}
//	
//	protected boolean isProductApplicabilitySatisfied(final Question question) {
//		Map<String, AccountProductApplicable> accountProducts = productsResolver.resolve();
//		for (AccountProductApplicable product : accountProducts.values()) {		
//			if (product.realize(question)) {
//				return true;
//			}
//		}
//		return false;
//	}	
	
	@Override
	public String getDisplayValue() {
		return "Debit Card";
	}

	public List<String> getProducts() {
		return applicableProducts;
	}

	public void setProducts(List<String> products) {
		this.applicableProducts = products;
	}

	@Override
	public List<String> resolveApplicableProducts() {
		return applicableProducts;
	}

	@Override
	public List<String> resolveApplicableRules() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
