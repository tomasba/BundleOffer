package seb.domain.bundle;

import java.util.List;

import seb.api.Displayable;

public class StudentBundle extends BaseBundleImpl implements Displayable {

	private List<String> products;
	private List<String> rules;
	
	public StudentBundle(List<String> rules, List<String> products) {
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
		return "Student";
	}

}
