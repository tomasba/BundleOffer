package seb.domain.rule;

import seb.api.customer.dto.Question;

public class IsStudent implements RuleApplicable {

	private Question question;
	
	public IsStudent() {		
	}
	
	public IsStudent(Question question) {
		this.question = question;
	}	
	
	@Override
	public boolean realize() {
		return (question != null && question.isStudent());
	}

	@Override
	public String toString() {
		return "IsStudent";
	}		
}
