package seb.domain.rule;

import seb.api.customer.dto.Question;

public class AgeLessThen18 implements RuleApplicable {

	private Question question;
	
	public AgeLessThen18(){		
	}
	
	public AgeLessThen18(Question question) {
		this.question = question;
	}
	
	@Override
	public boolean realize() {		
		return (question != null && question.getAge() < 18);
	}

	@Override
	public String toString() {
		return "AgeLessThen18";
	}
	
}
