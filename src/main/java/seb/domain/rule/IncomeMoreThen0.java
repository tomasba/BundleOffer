package seb.domain.rule;

import seb.api.dto.QuestionDto;
import seb.api.rule.RuleApplicable;

public class IncomeMoreThen0 implements RuleApplicable {

	private QuestionDto question;
	
	public IncomeMoreThen0() {		
	}
	
	public IncomeMoreThen0(QuestionDto question) {
		this.question = question;
	}	
	
	@Override
	public boolean realize() {
		return (question != null && question.isIncomeAttrProvided() && question.getIncome() > 0);
	}

}
