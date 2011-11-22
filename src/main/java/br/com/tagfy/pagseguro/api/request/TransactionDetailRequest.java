package br.com.tagfy.pagseguro.api.request;

import br.com.tagfy.pagseguro.api.conf.Config;

public class TransactionDetailRequest {
	
	private String transactionCode;
	
	private Config config;

	public TransactionDetailRequest(String transactionCode, Config config) {
		super();
		this.transactionCode = transactionCode;
		this.config = config;
	}
	
	public RequestBuilder getRequest() {
		String uri = config.getUrl()+"/"+transactionCode+"?email="+config.getEmail()+"&token="+config.getToken();
		return RequestBuilder.at(uri)
			.encoding(config.getEncoding());
	}

	public Config getConfig() {
		return config;
	}
	
}
