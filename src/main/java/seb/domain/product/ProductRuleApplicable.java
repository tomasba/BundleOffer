package seb.domain.product;

import java.util.List;

public interface ProductRuleApplicable extends ProductApplicable {		
	
	List<String> resolveApplicableRules();
	
}