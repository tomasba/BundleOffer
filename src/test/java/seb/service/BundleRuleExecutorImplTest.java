package seb.service;

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

import seb.api.bundle.BundleApplicable;
import seb.api.bundle.BundleComparable;
import seb.api.bundle.BundleRuleExecutor;
import seb.api.dto.QuestionDto;
import seb.domain.bundle.BaseBundleImpl;
import seb.domain.bundle.ClassicBundle;
import seb.domain.bundle.ClassicPlusBundle;
import seb.domain.bundle.GoldBundle;
import seb.domain.bundle.JuniorSaverBundle;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/business-config.xml")
public class BundleRuleExecutorImplTest {

	@Autowired
	BundleRuleExecutor service;
	
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void shouldProvideClassicPlusBundle() {
    	// given
    	QuestionDto question = new QuestionDto.QuestionBuilder(18).income(12001).student(true).build();
    	
    	// when
    	List<BundleApplicable> result = service.resolveBundles(question);
    	
    	// then
    	assertThat(result, notNullValue());
    	assertThat(result.size(), equalTo(1));
    	assertThat(result.get(0), notNullValue());
    	assertThat(result.get(0), instanceOf(BundleApplicable.class));
    	assertThat(result.get(0), instanceOf(BundleComparable.class));
    	assertThat(result.get(0), instanceOf(BaseBundleImpl.class));
    	assertThat(result.get(0), instanceOf(ClassicPlusBundle.class));
    	assertThat(result.get(0).isContainingAccountProduct(), equalTo(true));
    	assertThat(((BundleComparable)result.get(0)).resolvePriority(), equalTo(2));    	
    }       
	
    @Test
    public void shouldProvideJuniorSaverBundle() {
    	// given
    	QuestionDto question = new QuestionDto.QuestionBuilder(16).income(12001).build();
    	
    	// when
    	List<BundleApplicable> result = service.resolveBundles(question);
    	
    	// then
    	assertThat(result, notNullValue());
    	assertThat(result.size(), equalTo(1));
    	assertThat(result.get(0), notNullValue());
    	assertThat(result.get(0), instanceOf(BundleApplicable.class));
    	assertThat(result.get(0), instanceOf(BundleComparable.class));
    	assertThat(result.get(0), instanceOf(BaseBundleImpl.class));
    	assertThat(result.get(0), instanceOf(JuniorSaverBundle.class));
    	assertThat(result.get(0).isContainingAccountProduct(), equalTo(true));
    	assertThat(((BundleComparable)result.get(0)).resolvePriority(), equalTo(0));    	
    }
    
    @Test
    public void shouldProvideGoldBundle() {
    	// given
    	QuestionDto question = new QuestionDto.QuestionBuilder(26).income(40001).student(true).build();
    	
    	// when
    	List<BundleApplicable> result = service.resolveBundles(question);
    	
    	// then
    	assertThat(result, notNullValue());
    	assertThat(result.size(), equalTo(1));
    	assertThat(result.get(0), notNullValue());
    	assertThat(result.get(0), instanceOf(BundleApplicable.class));
    	assertThat(result.get(0), instanceOf(BundleComparable.class));
    	assertThat(result.get(0), instanceOf(BaseBundleImpl.class));
    	assertThat(result.get(0), instanceOf(GoldBundle.class));
    	assertThat(result.get(0).isContainingAccountProduct(), equalTo(true));
    	assertThat(((BundleComparable)result.get(0)).resolvePriority(), equalTo(3));    	
    }    
    
    @Test
    public void shouldNotProvideAnyBundle() {
    	// given
    	QuestionDto question = new QuestionDto.QuestionBuilder(26).income(-1).student(true).build();
    	
    	// when
    	List<BundleApplicable> result = service.resolveBundles(question);
    	
    	// then
    	assertThat(result, notNullValue());
    	//fails with CreditCard product rule for income > 12000
    	assertThat(result.size(), equalTo(0));    	
    }
    
    @Test
    public void shouldProvideClassicBundle() {
    	// given
    	QuestionDto question = new QuestionDto.QuestionBuilder(26).income(100).student(true).build();
    	
    	// when
    	List<BundleApplicable> result = service.resolveBundles(question);
    	
    	// then
    	assertThat(result, notNullValue());
    	assertThat(result.size(), equalTo(1));
    	assertThat(result.get(0), notNullValue());
    	assertThat(result.get(0), instanceOf(BundleApplicable.class));
    	assertThat(result.get(0), instanceOf(BundleComparable.class));
    	assertThat(result.get(0), instanceOf(BaseBundleImpl.class));
    	assertThat(result.get(0), instanceOf(ClassicBundle.class));
    	assertThat(result.get(0).isContainingAccountProduct(), equalTo(true));
    	assertThat(((BundleComparable)result.get(0)).resolvePriority(), equalTo(1));    	
    }    
}
