package seb.domain.product;

import java.util.List;

import seb.api.Displayable;
import seb.api.product.AccountProductApplicable;

public class JuniorSaverAccountProduct extends BaseProductImpl implements AccountProductApplicable, Displayable {

	private List<String> rules;
	
	public JuniorSaverAccountProduct(List<String> rules) {
		this.rules = rules;
	}
	
	@Override
	public String getDisplayValue() {
		return "Junior Saver Account";
	}

	@Override
	public List<String> resolveApplicableRules() {
		return rules;
	}

}
