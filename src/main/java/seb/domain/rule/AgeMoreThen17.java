package seb.domain.rule;

import seb.api.customer.dto.Question;

public class AgeMoreThen17 implements RuleApplicable {

	private Question question;
	
	public AgeMoreThen17() {		
	}
	
	public AgeMoreThen17(Question question) {
		this.question = question;
	}	
	
	@Override
	public boolean realize() {
		return (question != null && question.getAge() > 17);
	}
	
	@Override
	public String toString() {
		return "AgeMoreThen17";
	}	

}
