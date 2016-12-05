package seb.service;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

import seb.api.Displayable;
import seb.api.bundle.BundleApplicable;
import seb.api.bundle.BundleComparable;
import seb.api.bundle.BundleRealizationService;
import seb.api.bundle.BundleRuleExecutor;
import seb.api.dto.QuestionDto;

public class BundleRuleExecutorImpl implements BundleRuleExecutor {

	private static final Logger log = LoggerFactory.getLogger(BundleRuleExecutorImpl.class);
	
	private BundleRealizationService bundleRealizationService;
	
	@Override
	public List<BundleApplicable> resolveBundles(QuestionDto question) {
		log.info("***");
		log.info("STARTING populating available bundles based on customer answers: age={}, income={}, student={}", question.getAge(), question.getIncome(), question.isStudent());
		PriorityQueue<BundleApplicable> bundleQueue = bundleRealizationService.retrieveOrderedBundles(question);
		log.info("FINISHED populating available bundles.");
		log.info("Starting picking available bundles by additional rules.");
		List<BundleApplicable> bundleResult = new ArrayList<>();
		boolean bundleHasAccount = false;
		while (bundleQueue.iterator().hasNext()) {
			BundleApplicable b1 = bundleQueue.poll();
			BundleApplicable b2 = bundleQueue.peek();
			if (bundleHasAccount && b1.isContainingAccountProduct()) {
				continue;
			}
			bundleResult.add(b1);
			log.info("{} has been added to the list of applicable bundles for the client.", ((Displayable)b1).getDisplayValue());
			if (b2 == null) {
				log.info("Finished picking available bundles by additional rules.");		
				log.info("***");
				return bundleResult;
			}								
			if (((BundleComparable) b1).resolvePriority() > ((BundleComparable) b2).resolvePriority()) {
				log.info("Finished picking available bundles by additional rules.");		
				log.info("***");
				return bundleResult;
			}
			
			if (((BundleComparable) b1).resolvePriority() == ((BundleComparable) b2).resolvePriority()) {
				if (b1.isContainingAccountProduct()) {
					bundleHasAccount = true;
				}
				continue;
			}
		}
		log.info("Finished picking available bundles by additional rules.");		
		log.info("***");
		return bundleResult;		 
	}

	public BundleRealizationService getBundleRealizationService() {
		return bundleRealizationService;
	}

	@Required
	public void setBundleRealizationService(BundleRealizationService bundleRealizationService) {
		this.bundleRealizationService = bundleRealizationService;
	}
	
}
