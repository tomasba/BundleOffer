package seb.domain.product;

import java.util.List;

import seb.api.base.Displayable;

public class CreditCardProduct implements ProductRuleApplicable, CardProductApplicable, Displayable {

	private List<String> ruleBeanNames;
	
	public CreditCardProduct(List<String> rules) {
		this.ruleBeanNames = rules;
	}
	
	@Override
	public String getDisplayValue() {
		return "Credit Card";
	}

	@Override
	public List<String> resolveApplicableRules() {
		return ruleBeanNames;
	}

}
