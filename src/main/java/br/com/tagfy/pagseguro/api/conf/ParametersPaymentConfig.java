package br.com.tagfy.pagseguro.api.conf;

public class ParametersPaymentConfig implements PaymentConfig {
	
	private String email;
	
	private String token;
	
	private String encoding;
	
	private String url;
	
	public ParametersPaymentConfig(String email, String token, String encoding,
			String url) {
		super();
		this.email = email;
		this.token = token;
		this.encoding = encoding;
		this.url = url;
	}
	
	public String getEmail() {
		return email;
	}

	public String getToken() {
		return token;
	}

	public String getEncoding() {
		return encoding;
	}

	public String getUrl() {
		return url;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
