package seb.domain.product;

import java.util.List;

import seb.api.Displayable;
import seb.api.product.CardProductApplicable;

public class CreditCardProduct extends BaseProductImpl implements CardProductApplicable, Displayable {

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
