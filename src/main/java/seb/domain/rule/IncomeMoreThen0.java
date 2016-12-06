package seb.domain.rule;

import seb.api.customer.dto.Question;

public class IncomeMoreThen0 implements RuleApplicable {

	private Question question;
	
	public IncomeMoreThen0() {		
	}
	
	public IncomeMoreThen0(Question question) {
		this.question = question;
	}	
	
	@Override
	public boolean realize() {
		return (question != null && question.getIncome() > 0);
	}

	@Override
	public String toString() {
		return "IncomeMoreThen0";
	}		
}
