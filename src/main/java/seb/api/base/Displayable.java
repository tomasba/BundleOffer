package seb.api.base;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Entities implementing the interface must provide their corresponding display value (i.e. for end-user)
 * 
 * @author tbaltrukonis
 *
 */
public interface Displayable {
	@JsonProperty("Display value")
	String getDisplayValue();	
}