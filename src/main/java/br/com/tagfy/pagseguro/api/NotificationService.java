package br.com.tagfy.pagseguro.api;

import org.apache.http.HttpStatus;

import br.com.tagfy.pagseguro.api.conf.NotificationConfig;
import br.com.tagfy.pagseguro.api.model.Transaction;
import br.com.tagfy.pagseguro.api.utils.Get;

public class NotificationService {
	
	private NotificationConfig notificationConfig;
	
	private Get get;

	public NotificationService(NotificationConfig notificationConfig) {
		this.notificationConfig = notificationConfig;
	}
	
	public Transaction get(String notificationCode) throws Exception {
		get = Get.at(notificationConfig.getUrl()+"/"+notificationCode)
			.param("token", notificationConfig.getToken())
			.param("email", notificationConfig.getEmail())
			.encoding("ISO-8859-1")
			.get();
		
		if (get.getCode() == HttpStatus.SC_OK) {
			return get.resource(Transaction.class);
		} else {
			throw new Exception(get.getCode()+" - "+get.getReason());
		}
	}

}
