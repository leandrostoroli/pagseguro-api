package br.com.tagfy.pagseguro.api;

import org.apache.http.HttpStatus;

import br.com.tagfy.pagseguro.api.conf.PaymentConfig;
import br.com.tagfy.pagseguro.api.model.Payment;
import br.com.tagfy.pagseguro.api.model.PaymentError;
import br.com.tagfy.pagseguro.api.model.PaymentOrder;
import br.com.tagfy.pagseguro.api.model.PaymentOrderCode;
import br.com.tagfy.pagseguro.api.utils.Post;

public class PaymentService {
	
	private Post post;
	
	public PaymentService(PaymentConfig paymentConfig) {
		post = Post.at(paymentConfig.getUrl()+"?email="+paymentConfig.getEmail()+"&token="+paymentConfig.getToken())
			.contentType("application/xml; charset="+paymentConfig.getEncoding())
			.encoding(paymentConfig.getEncoding());
	}
	
	public PaymentOrder pay(Payment payment) throws Exception {
		post.param(payment);
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
