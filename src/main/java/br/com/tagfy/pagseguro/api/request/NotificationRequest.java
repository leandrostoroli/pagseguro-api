package br.com.tagfy.pagseguro.api.request;

import br.com.tagfy.pagseguro.api.conf.Config;

public class NotificationRequest {
	
	private String notificationCode;
	
	private Config config;
	
	public NotificationRequest(String notificationCode, Config config) {
		this.notificationCode = notificationCode;
		this.config = config;
	}
	
	public RequestBuilder getRequest() {
		String uri = config.getUrl()+"/"+notificationCode+"?email="+config.getEmail()+"&token="+config.getToken();
		return RequestBuilder.at(uri)
			.encoding(config.getEncoding());
	}

	public Config getConfig() {
		return config;
	}

}
