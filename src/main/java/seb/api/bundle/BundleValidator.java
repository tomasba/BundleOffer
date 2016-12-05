package seb.api.bundle;

import java.util.Map;

public interface BundleValidator {
	
	Map<String, String> validate(BundleDto bundleDto);
	
}
