package seb.domain.rule;

import seb.api.customer.dto.Question;

public class IncomeMoreThen12000 implements RuleApplicable {

	private Question question;	
	
	public IncomeMoreThen12000(Question question) {
		this.question = question;
	}
	
	public IncomeMoreThen12000() {
	}		
	
	@Override
	public boolean realize() {
		return (question != null && question.getIncome() > 12000);
	}

	@Override
	public String toString() {
		return "IncomeMoreThen12000";
	}		
}
