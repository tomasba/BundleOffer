package seb.domain.product;

import java.util.List;

import seb.api.Displayable;
import seb.api.product.AccountProductApplicable;

public class CurrentAccountPlusProduct extends BaseProductImpl implements AccountProductApplicable, Displayable {

	private List<String> ruleBeanNames;
	
	public CurrentAccountPlusProduct(List<String> rules) {
		this.ruleBeanNames = rules;
	}
	
	@Override
	public String getDisplayValue() {
		return "Current Account Plus";
	}

	@Override
	public List<String> resolveApplicableRules() {
		return ruleBeanNames;
	}

}
