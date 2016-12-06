package seb.domain.product;

import java.util.List;

import seb.api.base.Displayable;

public class JuniorSaverAccountProduct implements ProductRuleApplicable, AccountProductApplicable, Displayable {

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
