package seb.service.resolver.rule;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import seb.api.dto.QuestionDto;
import seb.api.rule.RuleApplicable;
import seb.api.rule.RulesResolver;

public class RulesResolverImpl implements RulesResolver, ApplicationContextAware {

	private ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Override
	public List<RuleApplicable> resolve(QuestionDto question) {
		List<RuleApplicable> rules = new ArrayList<>();		
		for (String questionBean : applicationContext.getBeanNamesForType(RuleApplicable.class)) {
			rules.add((RuleApplicable)applicationContext.getBean(questionBean, question));
		}
		return rules;
	}

	@Override
	public RuleApplicable resolveByName(String ruleBeanName, QuestionDto question) {
		return (RuleApplicable)applicationContext.getBean(ruleBeanName, question);
	}
}
