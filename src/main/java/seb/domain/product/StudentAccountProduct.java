package seb.domain.product;

import java.util.List;

import seb.api.Displayable;
import seb.api.product.AccountProductApplicable;

public class StudentAccountProduct extends BaseProductImpl implements AccountProductApplicable, Displayable {

	private List<String> ruleBeanNames;
	
	public StudentAccountProduct(List<String> rules) {
		this.ruleBeanNames = rules;
	}

	@Override
	public String getDisplayValue() {
		return "Student Account";
	}

	@Override
	public List<String> resolveApplicableRules() {
		return ruleBeanNames;
	}

}
