package seb.service.rule.resolver;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import seb.api.customer.dto.Question;
import seb.api.rule.resolver.RulesResolver;
import seb.domain.rule.RuleApplicable;

public class RulesResolverImpl implements RulesResolver, ApplicationContextAware {

	private ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Override
	public List<RuleApplicable> resolve(Question question) {
		List<RuleApplicable> rules = new ArrayList<>();		
		for (String questionBean : applicationContext.getBeanNamesForType(RuleApplicable.class)) {
			rules.add((RuleApplicable)applicationContext.getBean(questionBean, question));
		}
		return rules;
	}

	@Override
	public RuleApplicable resolveByUid(String ruleBeanName, Question question) {
		return (RuleApplicable)applicationContext.getBean(ruleBeanName, question);
	}
}
