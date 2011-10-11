package br.com.tagfy.pagseguro.api.model;

public class Phone {
	
	private String areaCode;
	
	private String number;
	
	public Phone() { }
	
	public Phone(String areaCode, String number) {
		super();
		this.areaCode = areaCode;
		this.number = number;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

}
