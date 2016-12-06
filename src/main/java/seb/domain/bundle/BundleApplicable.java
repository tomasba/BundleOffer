package seb.domain.bundle;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface BundleApplicable extends BundleComparable {
	
	boolean isContainingAccountProduct();
	@JsonProperty("Products")
	List<String> resolveApplicableProducts();
	@JsonProperty("Rules")
	List<String> resolveApplicableRules();	
	
}