package seb.domain.bundle;

import java.util.List;

import seb.api.Displayable;

public class JuniorSaverBundle extends BaseBundleImpl implements Displayable {

	private List<String> products;
	private List<String> rules;
	
	public JuniorSaverBundle(List<String> rules, List<String> products) {
		this.products = products;
		this.rules = rules;
	}

	@Override
	public List<String> resolveApplicableProducts() {		
		return products;
	}

	@Override
	public List<String> resolveApplicableRules() {
		return rules;
	}	
	
	@Override
	public int resolvePriority() {
		return 0;
	}

	@Override
	public String getDisplayValue() {
		return "Junior Saver";
	}

}