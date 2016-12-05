package seb.service.resolver.product;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import seb.api.product.ProductApplicable;
import seb.api.product.ProductResolver;

public class ProductsResolverImpl implements ProductResolver<ProductApplicable>, ApplicationContextAware {

	private static final Logger log = LoggerFactory.getLogger(ProductsResolverImpl.class);
	
	private ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Override
	public Map<String, ProductApplicable> resolve() {
		Map<String, ProductApplicable> products = new HashMap<String, ProductApplicable>();		
		for (String productBeanName : applicationContext.getBeanNamesForType(ProductApplicable.class)) {
			products.put(productBeanName, (ProductApplicable)applicationContext.getBean(productBeanName));
		}
		return products;
	}

	@Override
	public ProductApplicable resolveByName(String productBeanName) {
		try { 
			return (ProductApplicable)applicationContext.getBean(productBeanName);
		} catch (BeansException e) {
			log.error("Could not find registered product by bean name = {}", productBeanName, e);
			return null;
		} catch (Exception e) {
			log.error("Fatal error while looking for product by bean name = {}", productBeanName, e);
			return null;			
		}			
		
		
	}
}
