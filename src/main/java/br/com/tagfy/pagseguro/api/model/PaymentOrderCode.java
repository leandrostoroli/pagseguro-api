package br.com.tagfy.pagseguro.api.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="checkout")
public class PaymentOrderCode {
	
	private String code;
	
//	@XmlJavaTypeAdapter(value=DateUTCAdapter.class, type=Date.class)
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
