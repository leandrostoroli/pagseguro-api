package br.com.tagfy.pagseguro.api.model;

public class PaymentMethod {
	
	public static final Integer TYPE_CREDIT_CARD = 1;
	public static final Integer TYPE_BOLETO = 2;
	public static final Integer TYPE_ONLINE_TRANSFER = 3;
	public static final Integer TYPE_BALANCE = 4;
	public static final Integer TYPE_OI_PAGGO = 5;
	
	public static final Integer CODE_VISA_CREDIT_CARD = 101;
	public static final Integer CODE_MASTERCARD_CREDIT_CARD = 102;
	public static final Integer CODE_AMEX_CREDIT_CARD = 103;
	public static final Integer CODE_DINERS_CREDIT_CARD = 104;
	public static final Integer CODE_HIPERCARD_CREDIT_CARD = 105;
	public static final Integer CODE_AURA_CREDIT_CARD = 106;
	public static final Integer CODE_ELO_CREDIT_CARD = 107;
	public static final Integer CODE_BRADESCO_BOLETO = 201;
	public static final Integer CODE_SANTANDER_BOLETO = 202;
	public static final Integer CODE_BRADESCO_ONLINE_TRANSFER = 301;
	public static final Integer CODE_ITAU_ONLINE_TRANSFER = 302;
	public static final Integer CODE_UNIBANCO_ONLINE_TRANSFER = 303;
	public static final Integer CODE_BANCO_BRASIL_ONLINE_TRANSFER = 304;
	public static final Integer CODE_REAL_ONLINE_TRANSFER = 305;
	public static final Integer CODE_BANRISUL_ONLINE_TRANSFER = 306;
	public static final Integer CODE_PS_BALANCE = 401;
	public static final Integer CODE_OI_PAGGO = 501;
	
	private Integer type;
	
	private Integer code;
	
	public PaymentMethod() { }
	
	public PaymentMethod(Integer type, Integer code) {
		super();
		this.type = type;
		this.code = code;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

}
