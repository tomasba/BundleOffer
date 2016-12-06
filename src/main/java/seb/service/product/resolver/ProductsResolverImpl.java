package seb.service.product.resolver;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import seb.api.product.resolver.ProductResolver;
import seb.domain.product.ProductRuleApplicable;

public class ProductsResolverImpl implements ProductResolver<ProductRuleApplicable>, ApplicationContextAware {

	private static final Logger log = LoggerFactory.getLogger(ProductsResolverImpl.class);
	
	private ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Override
	public Map<String, ProductRuleApplicable> resolve() {
		Map<String, ProductRuleApplicable> products = new HashMap<String, ProductRuleApplicable>();		
		for (String productBeanName : applicationContext.getBeanNamesForType(ProductRuleApplicable.class)) {
			products.put(productBeanName, (ProductRuleApplicable)applicationContext.getBean(productBeanName));
		}
		return products;
	}

	@Override
	public ProductRuleApplicable resolveByUid(String productBeanName) {
		try { 
			return (ProductRuleApplicable)applicationContext.getBean(productBeanName);
		} catch (BeansException e) {
			log.error("Could not find registered product by bean name = {}", productBeanName, e);
			return null;
		} catch (Exception e) {
			log.error("Fatal error while looking for product by bean name = {}", productBeanName, e);
			return null;			
		}			
		
		
	}
}
