package seb.domain.bundle;

import java.util.List;

import seb.api.Displayable;

public class ClassicBundle extends BaseBundleImpl implements Displayable {

	private List<String> products;
	private List<String> rules;		
	
	public ClassicBundle(List<String> rules, List<String> products) {
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
	public String getDisplayValue() {
		return "Classic";
	}

	@Override
	public int resolvePriority() {
		return 1;
	}
}
