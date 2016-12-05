package seb.api.bundle;

import java.util.List;

import seb.domain.question.Question;

public class BundleDto {

	private String bundleName;	
	private List<String> productNames;
	private Question question;
	
	public String getBundleName() {
		return bundleName;
	}
	public void setBundleName(String bundleName) {
		this.bundleName = bundleName;
	}
	public List<String> getProductNames() {
		return productNames;
	}
	public void setProductNames(List<String> productNames) {
		this.productNames = productNames;
	}
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	} 
		

}
