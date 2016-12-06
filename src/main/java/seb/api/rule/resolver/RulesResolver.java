package seb.api.rule.resolver;

import java.util.List;

import seb.api.customer.dto.Question;
import seb.domain.rule.RuleApplicable;

public interface RulesResolver {

	List<RuleApplicable> resolve(Question question);
	
	RuleApplicable resolveByUid(String ruleBeanName, Question question);
	
}
