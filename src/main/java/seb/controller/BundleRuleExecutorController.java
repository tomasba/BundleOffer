package seb.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import seb.api.bundle.BundleApplicable;
import seb.api.bundle.BundleDto;
import seb.api.bundle.BundleRuleExecutor;
import seb.api.bundle.BundleValidator;
import seb.api.dto.QuestionDto;
import seb.domain.question.Question;

@Controller
@RequestMapping("/")
public class BundleRuleExecutorController {
	
	private static final Logger log = LoggerFactory.getLogger(BundleRuleExecutorController.class);
	
	@Autowired(required=true)
	private BundleRuleExecutor bundleRuleExecutor;
	
	@Autowired(required=true)
	private BundleValidator bundleValidator;

//	@RequestMapping(method = RequestMethod.GET)
//	public ResponseEntity<List<BundleApplicable>> findApplicableBundles(
//			@RequestParam(value = "age", required = true) int age,
//			@RequestParam(value = "student", required = false) boolean student,
//			@RequestParam(value = "income", required = false) double income) {
//
//		QuestionDto question = new QuestionDto.QuestionBuilder(age).income(income).student(student).build();
//		List<BundleApplicable> applicableBundles = bundleRuleExecutor.resolveBundles(question);
//		
//		return new ResponseEntity<List<BundleApplicable>>(applicableBundles, HttpStatus.CREATED);
//	}

	@RequestMapping(value="/bundle", method = RequestMethod.POST)
	public ResponseEntity<List<BundleApplicable>> loadApplicableBundles(@RequestBody Question q) {

		QuestionDto question = new QuestionDto.QuestionBuilder(q.getAge()).income(q.getIncome()).student(q.isStudent()).build();
		List<BundleApplicable> applicableBundles = bundleRuleExecutor.resolveBundles(question);
		
		return new ResponseEntity<List<BundleApplicable>>(applicableBundles, HttpStatus.CREATED);
	}
	
	
	@RequestMapping(value="/validation", method = RequestMethod.POST)
	public ResponseEntity<Map<String, String>> validateBundle(@RequestBody BundleDto bundleDto) {
		Map<String, String> result = bundleValidator.validate(bundleDto);
		if (result.isEmpty()) {
			log.info("The bundle name = {} details provided are valid.", bundleDto.getBundleName());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			log.info("The bundle name = {} details provided are not valid.", bundleDto.getBundleName());
			return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public BundleRuleExecutor getBundleRuleExecutor() {
		return bundleRuleExecutor;
	}

	public void setBundleRuleExecutor(BundleRuleExecutor bundleRuleExecutor) {
		this.bundleRuleExecutor = bundleRuleExecutor;
	}

	public BundleValidator getBundleValidator() {
		return bundleValidator;
	}

	public void setBundleValidator(BundleValidator bundleValidator) {
		this.bundleValidator = bundleValidator;
	}
	
}
