package seb.api.base;

public class ValidationResultStatus {

	private String validationType;
	
	private String validationMsg;

	public ValidationResultStatus(){		
	}
	
	public ValidationResultStatus(String validationType, String validationMsg){
		this.validationType = validationType;
		this.validationMsg = validationMsg;
	}
	
	public String getValidationType() {
		return validationType;
	}

	public void setValidationType(String validationType) {
		this.validationType = validationType;
	}

	public String getValidationMsg() {
		return validationMsg;
	}

	public void setValidationMsg(String validationMsg) {
		this.validationMsg = validationMsg;
	}
	
	
}
