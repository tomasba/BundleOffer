package seb.domain.rule;

import seb.api.dto.QuestionDto;
import seb.api.rule.RuleApplicable;

public class IncomeMoreThen40000 implements RuleApplicable {

	private QuestionDto question;
	
	public IncomeMoreThen40000(){		
	}
	
	public IncomeMoreThen40000(QuestionDto question) {
		this.question = question;
	}
	
	@Override
	public boolean realize() {
		return (question != null && question.isIncomeAttrProvided() && question.getIncome() > 40000);
	}

}
