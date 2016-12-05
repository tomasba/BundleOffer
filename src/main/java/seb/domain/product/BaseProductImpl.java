package seb.domain.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Required;

import seb.api.dto.QuestionDto;
import seb.api.product.ProductApplicable;
import seb.api.rule.RuleApplicable;
import seb.api.rule.RulesResolver;

public abstract class BaseProductImpl implements ProductApplicable {

	private RulesResolver rulesResolver;
	
	@Override
	public boolean realize(final QuestionDto question) {
		if (resolveApplicableRules() == null || resolveApplicableRules().isEmpty()) {
			return false;
		}
		for (String ruleBeanName : resolveApplicableRules()) {
			RuleApplicable rule = rulesResolver.resolveByName(ruleBeanName, question);
			if (!rule.realize()) {
				return false;
			}
		}
		return true;
	}		
	
	public abstract List<String> resolveApplicableRules();

	public RulesResolver getRulesResolver() {
		return rulesResolver;
	}

	@Required
	public void setRulesResolver(RulesResolver rulesResolver) {
		this.rulesResolver = rulesResolver;
	} 
	
}
