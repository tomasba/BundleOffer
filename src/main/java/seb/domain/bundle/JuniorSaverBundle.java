package seb.domain.bundle;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import seb.api.base.Displayable;

public class JuniorSaverBundle implements BundleApplicable, Displayable {

	private List<String> products;
	private List<String> rules;
	private boolean containsAccountProduct;
	
	public JuniorSaverBundle(List<String> rules, List<String> products, boolean containsAccountProduct) {
		this.products = products;
		this.rules = rules;
		this.containsAccountProduct = containsAccountProduct;
	}

	@Override
	public List<String> resolveApplicableProducts() {		
		return products;
	}

	@Override
	public List<String> resolveApplicableRules() {
		return rules;
	}	
	
	@JsonProperty("Priority")
	@Override
	public int resolvePriority() {
		return 0;
	}

	@Override
	public String getDisplayValue() {
		return "Junior Saver";
	}

	@Override
	public int compareTo(BundleComparable bundle) {
		return bundle.resolvePriority() - this.resolvePriority();
	}

	@Override
	public boolean isContainingAccountProduct() {
		return containsAccountProduct;
	}		
}