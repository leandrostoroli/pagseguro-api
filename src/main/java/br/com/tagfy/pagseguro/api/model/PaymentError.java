package br.com.tagfy.pagseguro.api.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="errors")
@XmlAccessorType(XmlAccessType.FIELD)
public class PaymentError {
	
	@XmlElement(name="error")
	private List<Error> errors;
	
	public PaymentError() {
		errors = new ArrayList<Error>();
	}
	
	public PaymentError error(String code, String message) {
		errors.add(new Error(code, message));
		return this;
	}

	public List<Error> getErrors() {
		return errors;
	}

	public void setErrors(List<Error> errors) {
		this.errors = errors;
	}

}
