package seb.api.bundle;

import java.util.Map;

public interface BundleResolver {

	Map<String, BundleApplicable> resolve();
	BundleApplicable resolveByName(String bundleBeanName);
	
}
