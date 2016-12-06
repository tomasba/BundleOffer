package seb.api.product.resolver;

import java.util.Map;

public interface ProductResolver<T> {

	Map<String, T> resolve();
	T resolveByUid(String productBeanName);
}
