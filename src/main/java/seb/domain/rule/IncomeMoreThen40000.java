package seb.domain.rule;

import seb.api.customer.dto.Question;

public class IncomeMoreThen40000 implements RuleApplicable {

	private Question question;
	
	public IncomeMoreThen40000(){		
	}
	
	public IncomeMoreThen40000(Question question) {
		this.question = question;
	}
	
	@Override
	public boolean realize() {
		return (question != null && question.getIncome() > 40000);
	}

	@Override
	public String toString() {
		return "IncomeMoreThen40000";
	}		
}
