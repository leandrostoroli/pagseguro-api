package br.com.tagfy.pagseguro.api.model;

public class Sender {
	
	private String name;
	
	private String email;
	
	private Phone phone;
	
	public Sender() { }
	
	public Sender(String name, String email, Phone phone) {
		super();
		this.name = name;
		this.email = email;
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Phone getPhone() {
		return phone;
	}

	public void setPhone(Phone phone) {
		this.phone = phone;
	}
	

}
