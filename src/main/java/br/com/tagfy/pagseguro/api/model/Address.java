package br.com.tagfy.pagseguro.api.model;

public class Address {
	
	private String street;
	
	private String number;
	
	private String complement;
	
	private String district;
	
	private String postalCode;
	
	private String city;
	
	private String state;
	
	private String country;
	
	public Address() { }
	
	public Address(String street, String number, String complement,
			String district, String postalCode, String city, String state,
			String country) {
		super();
		this.street = street;
		this.number = number;
		this.complement = complement;
		this.district = district;
		this.postalCode = postalCode;
		this.city = city;
		this.state = state;
		this.country = country;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
