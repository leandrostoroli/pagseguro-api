package br.com.tagfy.pagseguro.api;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import br.com.tagfy.pagseguro.api.conf.NotificationConfig;
import br.com.tagfy.pagseguro.api.model.Transaction;
import br.com.tagfy.pagseguro.api.utils.XmlUtils;

public class NotificationService {
	
	private NotificationConfig notificationConfig;

	public NotificationService(NotificationConfig notificationConfig) {
		this.notificationConfig = notificationConfig;
	}
	
	public Transaction get(String notificationCode) throws Exception {
		HttpClient client = new DefaultHttpClient();
		
		String url = notificationConfig.getUrl()+"/"+notificationCode+"?email="+notificationConfig.getEmail()+"&token="+notificationConfig.getToken();
		HttpGet notificationGetRequest = new HttpGet(url);
		
		try {
			HttpResponse notificationGetResponse = client.execute(notificationGetRequest);
			
			if (notificationGetResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				Transaction transaction = XmlUtils.fromXml(EntityUtils.toString(notificationGetResponse.getEntity()), Transaction.class);
				return transaction;
			} else {
				throw new Exception(notificationGetResponse.getStatusLine().getStatusCode()+" - "+notificationGetResponse.getStatusLine().getReasonPhrase());
			}
		} catch (ClientProtocolException e) {
			throw new Exception(e);
		} catch (IOException e) {
			throw new Exception(e);
		}
	}

}
