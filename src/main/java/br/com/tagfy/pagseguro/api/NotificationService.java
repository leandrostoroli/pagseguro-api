package br.com.tagfy.pagseguro.api;

import org.apache.http.HttpStatus;

import br.com.tagfy.pagseguro.api.model.Transaction;
import br.com.tagfy.pagseguro.api.request.NotificationRequest;
import br.com.tagfy.pagseguro.api.request.RequestBuilder;
import br.com.tagfy.pagseguro.api.utils.Get;


public class NotificationService {
	

	public static Transaction get(NotificationRequest notificationRequest) throws Exception {
		RequestBuilder requestBuilder = notificationRequest.getRequest();
		Get get = requestBuilder.buildGet();
		get.get();
		
		if (get.getCode() == HttpStatus.SC_OK) {
			return get.resource(Transaction.class);
		} else {
			throw new Exception(get.getCode()+" - "+get.getReason());
		}
	}

}
