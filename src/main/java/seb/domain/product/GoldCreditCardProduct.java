package seb.domain.product;

import java.util.List;

import seb.api.base.Displayable;

public class GoldCreditCardProduct implements ProductRuleApplicable, CardProductApplicable, Displayable {

	private List<String> rules;
	
	public GoldCreditCardProduct(List<String> questions) {
		this.rules = questions;
	}

	@Override
	public String getDisplayValue() {
		return "Credit Card";
	}

	@Override
	public List<String> resolveApplicableRules() {
		return rules;
	}

}