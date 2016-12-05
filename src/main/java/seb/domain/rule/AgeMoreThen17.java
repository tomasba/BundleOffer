package seb.domain.rule;

import seb.api.dto.QuestionDto;
import seb.api.rule.RuleApplicable;

public class AgeMoreThen17 implements RuleApplicable {

	private QuestionDto question;
	
	public AgeMoreThen17() {		
	}
	
	public AgeMoreThen17(QuestionDto question) {
		this.question = question;
	}	
	
	@Override
	public boolean realize() {
		return (question != null && question.isAgeAttrProvided() && question.getAge() > 17);
	}

}
