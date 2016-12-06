package seb.api.bundle.service;

import java.util.PriorityQueue;

import seb.api.customer.dto.Question;
import seb.domain.bundle.BundleApplicable;

public interface BundleApplicabilityService {

	PriorityQueue<BundleApplicable> retrieveOrderedBundles(Question question);	
	
}
