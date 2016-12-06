package seb.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import seb.api.bundle.service.CustomerBundleService;
import seb.api.customer.dto.Question;
import seb.domain.bundle.BundleApplicable;

@RestController
@RequestMapping("/")
public class BundleProviderController {

	@Autowired(required=true)
	private CustomerBundleService bundleRuleExecutor;
	
	@RequestMapping(value="/bundle", method = RequestMethod.POST)
	public ResponseEntity<List<BundleApplicable>> loadBestApplicableBundles(@RequestBody Question question) {
		List<BundleApplicable> applicableBundles = bundleRuleExecutor.resolveMostApplicableBundles(question);		
		return new ResponseEntity<List<BundleApplicable>>(applicableBundles, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/bundles", method = RequestMethod.POST)
	public ResponseEntity<List<BundleApplicable>> loadApplicableBundles(@RequestBody Question question) {
		List<BundleApplicable> applicableBundles = bundleRuleExecutor.resolveApplicableBundles(question);		
		return new ResponseEntity<List<BundleApplicable>>(applicableBundles, HttpStatus.CREATED);
	}	

	public CustomerBundleService getBundleRuleExecutor() {
		return bundleRuleExecutor;
	}

	public void setBundleRuleExecutor(CustomerBundleService bundleRuleExecutor) {
		this.bundleRuleExecutor = bundleRuleExecutor;
	}	
	
}
