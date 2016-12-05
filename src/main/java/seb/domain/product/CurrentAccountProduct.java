package seb.domain.product;

import java.util.List;

import seb.api.Displayable;
import seb.api.product.AccountProductApplicable;

public class CurrentAccountProduct extends BaseProductImpl implements AccountProductApplicable, Displayable {

	private List<String> ruleBeanNames;
	
	public CurrentAccountProduct(List<String> rules) {
		this.ruleBeanNames = rules;
	}
	
	@Override
	public String getDisplayValue() {
		return "Current Account";
	}

	@Override
	public List<String> resolveApplicableRules() {
		return ruleBeanNames;
	}

}
