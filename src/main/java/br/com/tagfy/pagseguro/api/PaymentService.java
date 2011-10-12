package br.com.tagfy.pagseguro.api;

import org.apache.http.HttpStatus;

import br.com.tagfy.pagseguro.api.model.PaymentError;
import br.com.tagfy.pagseguro.api.model.PaymentOrder;
import br.com.tagfy.pagseguro.api.model.PaymentOrderCode;
import br.com.tagfy.pagseguro.api.request.PaymentRequest;
import br.com.tagfy.pagseguro.api.request.RequestBuilder;
import br.com.tagfy.pagseguro.api.utils.Post;

public class PaymentService {
	
	public static PaymentOrder pay(PaymentRequest paymentRequest) throws Exception {
		RequestBuilder requestBuilder = paymentRequest.getRequest();
		Post post = requestBuilder.buildPost();
		post.post();
		
		PaymentOrder order = new PaymentOrder();
		
		if (post.getCode() == HttpStatus.SC_OK) {
			PaymentOrderCode orderCode = post.resource(PaymentOrderCode.class);
			order.setOrderCode(orderCode);
		} else if (post.getCode() == HttpStatus.SC_BAD_REQUEST) {
			PaymentError error = post.resource(PaymentError.class);
			order.setError(error);
		} else {
			throw new Exception(post.getCode()+" - "+post.getReason());
		}
		
		return order;
	}
	
}
