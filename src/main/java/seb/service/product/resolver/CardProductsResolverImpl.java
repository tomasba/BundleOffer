package seb.service.product.resolver;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import seb.api.product.resolver.ProductResolver;
import seb.domain.product.CardProductApplicable;

public class CardProductsResolverImpl implements ProductResolver<CardProductApplicable>, ApplicationContextAware {

	private static final Logger log = LoggerFactory.getLogger(CardProductsResolverImpl.class);
	
	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Override
	public Map<String, CardProductApplicable> resolve() {
		Map<String, CardProductApplicable> products = new HashMap<String, CardProductApplicable>();		
		for (String productBeanName : applicationContext.getBeanNamesForType(CardProductApplicable.class)) {
			products.put(productBeanName, (CardProductApplicable)applicationContext.getBean(productBeanName));
		}
		return products;
	}

	@Override
	public CardProductApplicable resolveByUid(String productBeanName) {
		try { 
			return (CardProductApplicable)applicationContext.getBean(productBeanName);
		} catch (BeansException e) {
			log.error("Could not find registered product by bean name = {}", productBeanName, e);
			return null;
		} catch (Exception e) {
			log.error("Fatal error while looking for product by bean name = {}", productBeanName, e);
			return null;			
		}					
	}	
	
}
