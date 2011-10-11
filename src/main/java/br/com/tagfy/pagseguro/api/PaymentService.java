package br.com.tagfy.pagseguro.api;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import br.com.tagfy.pagseguro.api.conf.PaymentConfig;
import br.com.tagfy.pagseguro.api.model.Payment;
import br.com.tagfy.pagseguro.api.model.PaymentError;
import br.com.tagfy.pagseguro.api.model.PaymentOrder;
import br.com.tagfy.pagseguro.api.model.PaymentOrderCode;
import br.com.tagfy.pagseguro.api.utils.HttpUtils;
import br.com.tagfy.pagseguro.api.utils.XmlUtils;

public class PaymentService {
	
	private PaymentConfig paymentConfig;
	
	public PaymentService(PaymentConfig paymentConfig) {
		this.paymentConfig = paymentConfig;
	}
	
	public PaymentOrder pay(Payment payment) throws Exception {
		HttpClient client = new DefaultHttpClient();
		
		String url = paymentConfig.getUrl()+"?email="+paymentConfig.getEmail()+"&token="+paymentConfig.getToken();
		HttpPost paymentPostRequest = new HttpPost(url);
		
		try {
			StringEntity xmlEntity = new StringEntity(XmlUtils.toXml(payment, paymentConfig.getEncoding()), paymentConfig.getEncoding());
			
			paymentPostRequest.addHeader("Content-type", "application/xml; charset="+paymentConfig.getEncoding());
			paymentPostRequest.setEntity(xmlEntity);
			
			HttpUtils.toString(paymentPostRequest);
			
			HttpResponse paymentPostResponse = client.execute(paymentPostRequest);
			
			PaymentOrder order = new PaymentOrder();
			
			if (paymentPostResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				PaymentOrderCode orderCode = XmlUtils.fromXml(EntityUtils.toString(paymentPostResponse.getEntity()), PaymentOrderCode.class);
				order.setOrderCode(orderCode);
			} else if (paymentPostResponse.getStatusLine().getStatusCode() == HttpStatus.SC_BAD_REQUEST) {
				PaymentError error = XmlUtils.fromXml(EntityUtils.toString(paymentPostResponse.getEntity()), PaymentError.class);
				order.setError(error);
			} else {
				throw new Exception(paymentPostResponse.getStatusLine().getStatusCode()+" - "+paymentPostResponse.getStatusLine().getReasonPhrase());
			}
			
			return order;
		} catch (UnsupportedEncodingException e) {
			throw new Exception(e);
		} catch (ClientProtocolException e) {
			throw new Exception(e);
		} catch (IOException e) {
			throw new Exception(e);
		} catch (Exception e) {
			throw new Exception(e);
		} 
	}
	
}
