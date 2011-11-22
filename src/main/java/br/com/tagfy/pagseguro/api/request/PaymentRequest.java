package br.com.tagfy.pagseguro.api.request;

import br.com.tagfy.pagseguro.api.conf.PaymentConfig;
import br.com.tagfy.pagseguro.api.model.Payment;

public class PaymentRequest {
	
	private Payment payment;
	
	private PaymentConfig config;
	
	public PaymentRequest(Payment payment, PaymentConfig config) {
		this.config = config;
		this.payment = payment;
	}

	public RequestBuilder getRequest() {
		String uri = config.getUrl()+"?email="+config.getEmail()+"&token="+config.getToken();
		String contentType = "application/xml; charset="+config.getEncoding();
		return RequestBuilder.at(uri)
			.contentType(contentType)
			.encoding(config.getEncoding())
			.param(payment);
	}

	public PaymentConfig getConfig() {
		return config;
	}

}
