package com.saraya.exception;



public class ErrorMessage {

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
//	public static ErrorMessage prepareErrorMessageEntity(ErrorMessageDTO errorMessageDTO) {
//		
//		ErrorMessage errorMessageEntity = new ErrorMessage();
//		errorMessageEntity.setErrorMessage(errorMessageDTO.getErrorMessage());
//		
//		return errorMessageEntity;
//	}
}
