package br.com.tagfy.pagseguro.api.request;

import br.com.tagfy.pagseguro.api.conf.Config;
import br.com.tagfy.pagseguro.api.model.Payment;

public class PaymentRequest {
	
	private Payment payment;
	
	private Config config;
	
	public PaymentRequest(Payment payment, Config config) {
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

}
