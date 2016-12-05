package seb.domain.rule;

import seb.api.dto.QuestionDto;
import seb.api.rule.RuleApplicable;

public class AgeLessThen18 implements RuleApplicable {

	private QuestionDto question;
	
	public AgeLessThen18(){		
	}
	
	public AgeLessThen18(QuestionDto question) {
		this.question = question;
	}
	
	@Override
	public boolean realize() {		
		return (question != null && question.isAgeAttrProvided() && question.getAge() < 18);
	}

}
