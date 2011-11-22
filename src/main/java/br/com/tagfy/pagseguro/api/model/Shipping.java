package br.com.tagfy.pagseguro.api.model;

import java.math.BigDecimal;


public class Shipping {
	
	public static final int PAC = 1;
	public static final int SEDEX = 2;
	public static final int NOT_ESPECIFIED = 3;
	
	private Integer type;
	
	private Address address;
	
	private BigDecimal cost;
	
	public Shipping() { }
	
	public Shipping(Integer type, Address address, BigDecimal cost) {
		super();
		this.type = type;
		this.address = address;
		this.cost = cost;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

}
