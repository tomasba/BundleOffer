package seb.service;

import java.util.Map;
import java.util.PriorityQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

import seb.api.Displayable;
import seb.api.bundle.BundleApplicable;
import seb.api.bundle.BundleRealizationService;
import seb.api.bundle.BundleResolver;
import seb.api.dto.QuestionDto;

public class BundleRealizationServiceImpl implements BundleRealizationService {

	private static final Logger log = LoggerFactory.getLogger(BundleRealizationServiceImpl.class);
	
	private BundleResolver bundleResolver;	
	
	@Override
	public PriorityQueue<BundleApplicable> retrieveOrderedBundles(QuestionDto question) {
		Map<String, BundleApplicable> bundles = bundleResolver.resolve();		
		PriorityQueue<BundleApplicable> pq = new PriorityQueue<>();		
		for (BundleApplicable bundle : bundles.values()) {
			if (bundle.realize(question)) {
				log.info(((Displayable)bundle).getDisplayValue() + " - has been added.");
				pq.add(bundle);
			} else {
				log.info(((Displayable)bundle).getDisplayValue() + " - has not been added. Applicability rules are not satisfied.");
			}
		}				
		return pq;		
	}
	
	public BundleResolver getBundleResolver() {
		return bundleResolver;
	}

	@Required
	public void setBundleResolver(BundleResolver bundleResolver) {
		this.bundleResolver = bundleResolver;
	}	
}
