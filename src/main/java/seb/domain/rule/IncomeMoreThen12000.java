package seb.domain.rule;

import seb.api.dto.QuestionDto;
import seb.api.rule.RuleApplicable;

public class IncomeMoreThen12000 implements RuleApplicable {

	private QuestionDto question;	
	
	public IncomeMoreThen12000(QuestionDto question) {
		this.question = question;
	}
	
	public IncomeMoreThen12000() {
	}		
	
	@Override
	public boolean realize() {
		return (question != null && question.isIncomeAttrProvided() && question.getIncome() > 12000);
	}

}
