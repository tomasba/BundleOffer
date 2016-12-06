package seb.service.bundle;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

import com.google.common.collect.Lists;

import seb.api.base.Displayable;
import seb.api.bundle.service.BundleApplicabilityService;
import seb.api.bundle.service.CustomerBundleService;
import seb.api.customer.dto.Question;
import seb.domain.bundle.BundleApplicable;
import seb.domain.bundle.BundleComparable;

public class CustomerBundleServiceImpl implements CustomerBundleService {

	private static final Logger log = LoggerFactory.getLogger(CustomerBundleServiceImpl.class);
	
	private BundleApplicabilityService bundleRealizationService;
	
	@Override
	public List<BundleApplicable> resolveApplicableBundles(Question question) {
		List<BundleApplicable> result = Lists.newArrayList();
		PriorityQueue<BundleApplicable> bundlesQueue = findAvailableCustomerBundles(question);
		Iterator<BundleApplicable> i = bundlesQueue.iterator();
		while (i.hasNext()) {
			result.add(bundlesQueue.poll());
		}
		return result;
	}		
	
	@Override
	public List<BundleApplicable> resolveMostApplicableBundles(Question question) {
		PriorityQueue<BundleApplicable> bundleQueue = findAvailableCustomerBundles(question);
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

	private PriorityQueue<BundleApplicable> findAvailableCustomerBundles(Question question) {
		log.info("***");
		log.info("STARTING populating available bundles based on customer answers: age={}, income={}, student={}", question.getAge(), question.getIncome(), question.isStudent());
		PriorityQueue<BundleApplicable> bundleQueue = bundleRealizationService.retrieveOrderedBundles(question);
		log.info("FINISHED populating available bundles.");
		return bundleQueue;
	}	

	public BundleApplicabilityService getBundleRealizationService() {
		return bundleRealizationService;
	}

	@Required
	public void setBundleRealizationService(BundleApplicabilityService bundleRealizationService) {
		this.bundleRealizationService = bundleRealizationService;
	}
	
}
