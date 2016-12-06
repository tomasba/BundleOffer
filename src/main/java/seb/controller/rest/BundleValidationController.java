package seb.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;

import seb.api.base.ValidationResultStatus;
import seb.api.bundle.service.BundleValidationService;
import seb.api.product.service.ProductValidationService;
import seb.controller.dto.BundleRequestDto;

@RestController
@RequestMapping("/")
public class BundleValidationController {

	@Autowired(required=true)
	private BundleValidationService bundleValidationService;
	
	@Autowired(required=true)
	private ProductValidationService productValidationService;	
	
	@RequestMapping(value="/validation", method = RequestMethod.POST)
	public ResponseEntity<List<ValidationResultStatus>> validateBundle(@RequestBody BundleRequestDto bundleDto) {
		List<ValidationResultStatus> result = Lists.newArrayList();
		
		result = bundleIsAvailable(bundleDto, result);
		result = bundleSupportsProducts(bundleDto, result);		
		result = productRulesConformToCustomer(bundleDto, result);		
		result = bundleRulesConformToCustomer(bundleDto, result);
		
		return result.isEmpty() ? new ResponseEntity<List<ValidationResultStatus>>(result, HttpStatus.OK) :
			new ResponseEntity<List<ValidationResultStatus>>(result, HttpStatus.BAD_REQUEST);
	}
	
	protected List<ValidationResultStatus> bundleRulesConformToCustomer(BundleRequestDto bundleDto, List<ValidationResultStatus> result) {
		return appendToViolationRresultIfApplicable(result, 
				bundleValidationService.validateBundleWithRules(bundleDto.getQuestion(), bundleDto.getBundleUid()));
	}

	protected List<ValidationResultStatus> productRulesConformToCustomer(BundleRequestDto bundleDto, List<ValidationResultStatus> result) {		
		return appendToViolationRresultIfApplicable(result, 
				productValidationService.validateProductWithRules(bundleDto.getQuestion(), bundleDto.getProductUids()));
	}

	protected List<ValidationResultStatus> bundleSupportsProducts(BundleRequestDto bundleDto, List<ValidationResultStatus> result) {		
		return appendToViolationRresultIfApplicable(result, 
				bundleValidationService.validateProductSupportedByBundle(bundleDto.getProductUids(), bundleDto.getBundleUid()));
	}

	protected List<ValidationResultStatus> bundleIsAvailable(BundleRequestDto bundleDto, List<ValidationResultStatus> result) {
		return appendToViolationRresultIfApplicable(result, 
				bundleValidationService.validateBundleAvailable(bundleDto.getBundleUid()));
	}

	private List<ValidationResultStatus> appendToViolationRresultIfApplicable(List<ValidationResultStatus> result, List<ValidationResultStatus> r) {
		if (!r.isEmpty()) {
			result.addAll(r);
		}
		return result;
	}	
	
}