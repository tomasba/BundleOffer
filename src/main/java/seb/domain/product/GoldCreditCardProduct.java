package seb.domain.product;

import java.util.List;

import seb.api.Displayable;
import seb.api.product.CardProductApplicable;

public class GoldCreditCardProduct extends BaseProductImpl implements CardProductApplicable, Displayable {

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