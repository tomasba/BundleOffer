package seb.api.product;

import java.util.Map;

public interface ProductResolver<T> {

	Map<String, T> resolve();
	T resolveByName(String productBeanName);
}
