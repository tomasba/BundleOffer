package seb.service.bundle;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.notNullValue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import seb.api.bundle.service.CustomerBundleService;
import seb.api.customer.dto.Question;
import seb.domain.bundle.BundleApplicable;
import seb.domain.bundle.BundleComparable;
import seb.domain.bundle.ClassicBundle;
import seb.domain.bundle.ClassicPlusBundle;
import seb.domain.bundle.GoldBundle;
import seb.domain.bundle.JuniorSaverBundle;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/*-config.xml")
public class CustomerBundleServiceImplTest {

	@Autowired
	CustomerBundleService service;
	
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void shouldProvideClassicPlusBundle() {
    	// given
    	Question question = new Question(18, true, 12001);
    	
    	// when
    	List<BundleApplicable> result = service.resolveMostApplicableBundles(question);
    	
    	// then
    	assertThat(result, notNullValue());
    	assertThat(result.size(), equalTo(1));
    	assertThat(result.get(0), notNullValue());
    	assertThat(result.get(0), instanceOf(BundleApplicable.class));
    	assertThat(result.get(0), instanceOf(BundleComparable.class));
    	assertThat(result.get(0), instanceOf(ClassicPlusBundle.class));
    	assertThat(result.get(0).isContainingAccountProduct(), equalTo(true));
    	assertThat(((BundleComparable)result.get(0)).resolvePriority(), equalTo(2));    	
    }       
	
    @Test
    public void shouldProvideJuniorSaverBundle() {
    	// given
    	Question question = new Question(16);
    	question.setIncome(12001);
    	
    	// when
    	List<BundleApplicable> result = service.resolveMostApplicableBundles(question);
    	
    	// then
    	assertThat(result, notNullValue());
    	assertThat(result.size(), equalTo(1));
    	assertThat(result.get(0), notNullValue());
    	assertThat(result.get(0), instanceOf(BundleApplicable.class));
    	assertThat(result.get(0), instanceOf(BundleComparable.class));
    	assertThat(result.get(0), instanceOf(JuniorSaverBundle.class));
    	assertThat(result.get(0).isContainingAccountProduct(), equalTo(true));
    	assertThat(((BundleComparable)result.get(0)).resolvePriority(), equalTo(0));    	
    }
    
    @Test
    public void shouldProvideGoldBundle() {
    	// given
    	Question question = new Question(26, true, 40001);
    	
    	// when
    	List<BundleApplicable> result = service.resolveMostApplicableBundles(question);
    	
    	// then
    	assertThat(result, notNullValue());
    	assertThat(result.size(), equalTo(1));
    	assertThat(result.get(0), notNullValue());
    	assertThat(result.get(0), instanceOf(BundleApplicable.class));
    	assertThat(result.get(0), instanceOf(BundleComparable.class));
    	assertThat(result.get(0), instanceOf(GoldBundle.class));
    	assertThat(result.get(0).isContainingAccountProduct(), equalTo(true));
    	assertThat(((BundleComparable)result.get(0)).resolvePriority(), equalTo(3));    	
    }    
    
    @Test
    public void shouldNotProvideAnyBundle() {
    	// given
    	Question question = new Question(26, true, -1);
    	
    	// when
    	List<BundleApplicable> result = service.resolveMostApplicableBundles(question);
    	
    	// then
    	assertThat(result, notNullValue());
    	//fails with CreditCard product rule for income > 12000
    	assertThat(result.size(), equalTo(0));    	
    }
    
    @Test
    public void shouldProvideClassicBundle() {
    	// given
    	Question question = new Question(26, true, 100);
    	
    	// when
    	List<BundleApplicable> result = service.resolveMostApplicableBundles(question);
    	
    	// then
    	assertThat(result, notNullValue());
    	assertThat(result.size(), equalTo(1));
    	assertThat(result.get(0), notNullValue());
    	assertThat(result.get(0), instanceOf(BundleApplicable.class));
    	assertThat(result.get(0), instanceOf(BundleComparable.class));
    	assertThat(result.get(0), instanceOf(ClassicBundle.class));
    	assertThat(result.get(0).isContainingAccountProduct(), equalTo(true));
    	assertThat(((BundleComparable)result.get(0)).resolvePriority(), equalTo(1));    	
    }    
    
    @Test
    public void shouldProvideFullListOfAllApplicableBundles() {
    	// given
    	Question question = new Question(33, true, 1000001);
    	
    	// when
    	List<BundleApplicable> result = service.resolveApplicableBundles(question);
    	
    	// then
    	assertThat(result, notNullValue());
    	assertThat(result.size(), equalTo(4));
    }
    
    @Test
    public void shouldProvideFullListOfAllApplicableBundlesWhenAgeIsBelow18() {
    	// given
    	Question question = new Question(17, true, 1000001);
    	
    	// when
    	List<BundleApplicable> result = service.resolveApplicableBundles(question);
    	
    	// then
    	assertThat(result, notNullValue());
    	assertThat(result.size(), equalTo(1));
    	assertThat(result.get(0), notNullValue());
    	assertThat(result.get(0), instanceOf(JuniorSaverBundle.class));    	    	
    }    
}
