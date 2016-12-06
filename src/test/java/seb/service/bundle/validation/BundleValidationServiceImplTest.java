package seb.service.bundle.validation;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.BDDMockito.given;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.common.collect.Lists;

import seb.api.base.ValidationCodes;
import seb.api.base.ValidationResultStatus;
import seb.api.bundle.resolver.BundleResolver;
import seb.api.bundle.service.BundleValidationService;
import seb.domain.bundle.BundleApplicable;
import seb.service.validator.bundle.BundleRuleValidator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/*-config.xml")
public class BundleValidationServiceImplTest {

	@Autowired
	@InjectMocks
	private BundleValidationService service;
	@Mock
	private BundleResolver bundleResolver;
	@Mock
	private BundleRuleValidator bundleRuleValidator;	
	
	@Mock
	private BundleApplicable bundle;
	
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }	
    
    @Test
    public void shouldFillValidationDetailsWhenBundleNotFound() {
    	// given
    	String bundleUid = "bundleUid";
    	given(bundleResolver.resolveByUid(bundleUid)).willReturn(null);
    	
    	// when
    	List<ValidationResultStatus> result = service.validateBundleAvailable(bundleUid);
    	
    	// then
    	assertThat(result, notNullValue());
    	assertThat(result.size(), equalTo(1));
    	assertThat(result.get(0), notNullValue());
    	assertThat(result.get(0).getValidationType(), equalTo(ValidationCodes.BUNDLE_NOT_FOND));
    }
    
    @Test
    public void shouldValidateBundleWithRules() {
    	// given
    	String bundleUid = "bundleUid";
    	String productUid = "productUid";
    	given(bundleResolver.resolveByUid(bundleUid)).willReturn(bundle);
    	List<String> emptyApplicableProducts = Lists.newArrayList();
    	given(bundle.resolveApplicableProducts()).willReturn(emptyApplicableProducts);
    	
    	// when
    	List<ValidationResultStatus> result = service.validateProductSupportedByBundle(productUid, bundleUid);
    	
    	// then
    	assertThat(result, notNullValue());
    	assertThat(result.size(), equalTo(1));
    	assertThat(result.get(0), notNullValue());
    	assertThat(result.get(0).getValidationType(), equalTo(ValidationCodes.BUNDLE_CONFIG));    	
    }
	
}
