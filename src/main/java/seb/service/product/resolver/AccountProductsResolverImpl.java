package seb.service.product.resolver;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import seb.api.product.resolver.ProductResolver;
import seb.domain.product.AccountProductApplicable;

public class AccountProductsResolverImpl implements ProductResolver<AccountProductApplicable>, ApplicationContextAware {

	private static final Logger log = LoggerFactory.getLogger(AccountProductsResolverImpl.class);
	
	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Override
	public Map<String, AccountProductApplicable> resolve() {
		Map<String, AccountProductApplicable> products = new HashMap<String, AccountProductApplicable>();		
		for (String productBeanName : applicationContext.getBeanNamesForType(AccountProductApplicable.class)) {
			products.put(productBeanName, (AccountProductApplicable)applicationContext.getBean(productBeanName));
		}
		return products;
	}

	@Override
	public AccountProductApplicable resolveByUid(String productBeanName) {
		try { 
			return (AccountProductApplicable)applicationContext.getBean(productBeanName);
		} catch (BeansException e) {
			log.error("Could not find registered product by bean name = {}", productBeanName, e);
			return null;
		} catch (Exception e) {
			log.error("Fatal error while looking for product by bean name = {}", productBeanName, e);
			return null;			
		}		
	}

}
