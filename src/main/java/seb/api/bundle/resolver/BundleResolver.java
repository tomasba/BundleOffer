package seb.api.bundle.resolver;

import java.util.Map;

import seb.domain.bundle.BundleApplicable;

public interface BundleResolver {

	Map<String, BundleApplicable> resolve();
	BundleApplicable resolveByUid(String bundleBeanName);
	
}
