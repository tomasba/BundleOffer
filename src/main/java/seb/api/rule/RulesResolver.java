package seb.api.rule;

import java.util.List;

import seb.api.dto.QuestionDto;

public interface RulesResolver {

	List<RuleApplicable> resolve(QuestionDto question);
	
	RuleApplicable resolveByName(String ruleBeanName, QuestionDto question);
	
}
