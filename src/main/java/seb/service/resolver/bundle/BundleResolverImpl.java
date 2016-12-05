package seb.service.resolver.bundle;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import seb.api.bundle.BundleApplicable;
import seb.api.bundle.BundleResolver;

public class BundleResolverImpl implements BundleResolver, ApplicationContextAware {

	private static final Logger log = LoggerFactory.getLogger(BundleResolverImpl.class);
	
	private ApplicationContext applicationContext;
	
	@Override
	public Map<String, BundleApplicable> resolve() {
		Map<String, BundleApplicable> bundles = new HashMap<String, BundleApplicable>();		
		for (String bundleBeanName : applicationContext.getBeanNamesForType(BundleApplicable.class)) {
			bundles.put(bundleBeanName, (BundleApplicable)applicationContext.getBean(bundleBeanName));
		}
		return bundles;
	}

	@Override
	public BundleApplicable resolveByName(String bundleBeanName) {
		try { 
			return (BundleApplicable)applicationContext.getBean(bundleBeanName);
		} catch (BeansException e) {
			log.error("Could not find registered bundle by bean name = {}", bundleBeanName, e);
			return null;
		} catch (Exception e) {
			log.error("Fatal error while looking for bundle by bean name = {}", bundleBeanName, e);
			return null;			
		}	
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

}
