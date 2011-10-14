package br.com.tagfy.pagseguro.api.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import br.com.tagfy.pagseguro.api.utils.DateUTCAdapter;

@XmlRootElement(name="checkout")
public class PaymentOrderCode {
	
	private String code;
	
	@XmlJavaTypeAdapter(value=DateUTCAdapter.class, type=Date.class)
	private String date;
	
	public PaymentOrderCode() { }
	
	public PaymentOrderCode(String code) {
		super();
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
