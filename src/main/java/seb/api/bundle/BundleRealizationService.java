package seb.api.bundle;

import java.util.PriorityQueue;

import seb.api.dto.QuestionDto;

public interface BundleRealizationService {

	PriorityQueue<BundleApplicable> retrieveOrderedBundles(QuestionDto question);	
	
}
