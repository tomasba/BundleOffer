package seb.domain.rule;

import seb.api.dto.QuestionDto;
import seb.api.rule.RuleApplicable;

public class IsStudent implements RuleApplicable {

	private QuestionDto question;
	
	public IsStudent() {		
	}
	
	public IsStudent(QuestionDto question) {
		this.question = question;
	}	
	
	@Override
	public boolean realize() {
		return (question != null && question.isStudentAttrProvided() && question.isStudent());
	}

}
