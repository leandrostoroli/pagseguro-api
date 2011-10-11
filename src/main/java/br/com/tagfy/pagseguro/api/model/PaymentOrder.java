package br.com.tagfy.pagseguro.api.model;


public class PaymentOrder {
	
	private PaymentOrderCode orderCode;
	
	private PaymentError error;
	
	public PaymentOrder() { }
	
	public PaymentOrderCode getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(PaymentOrderCode orderCode) {
		this.orderCode = orderCode;
	}

	public PaymentError getError() {
		return error;
	}

	public void setError(PaymentError error) {
		this.error = error;
	}

}
