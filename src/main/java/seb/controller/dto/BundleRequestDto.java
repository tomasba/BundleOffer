package seb.controller.dto;

import java.util.List;

import seb.api.customer.dto.Question;

public class BundleRequestDto {

	private String bundleUid;	
	private List<String> productUids;
	private Question question;
	
	public String getBundleUid() {
		return bundleUid;
	}
	public void setBundleUid(String bundleUid) {
		this.bundleUid = bundleUid;
	}
	public List<String> getProductUids() {
		return productUids;
	}
	public void setProductUids(List<String> productUids) {
		this.productUids = productUids;
	}
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	} 
		

}
