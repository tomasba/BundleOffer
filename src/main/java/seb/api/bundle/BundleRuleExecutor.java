package seb.api.bundle;

import java.util.List;

import seb.api.dto.QuestionDto;

public interface BundleRuleExecutor {

	public List<BundleApplicable> resolveBundles(QuestionDto question);
	
}