package br.com.tagfy.pagseguro.api.conf;

public interface PaymentConfig extends Config {
	
	String getPaymentUrl(String code);

}
