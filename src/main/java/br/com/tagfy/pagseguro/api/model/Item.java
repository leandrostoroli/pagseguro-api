package br.com.tagfy.pagseguro.api.model;

import java.math.BigDecimal;

public class Item {
	
	private String id;
	
	private String description;
	
	private Integer quantity;
	
	private BigDecimal amount;
	
	private Long weight;
	
	private BigDecimal shippingCost;
	
	public Item() { }
	
	public Item(String id, String description, Integer quantity,
			BigDecimal amount, Long weight, BigDecimal shippingCost) {
		super();
		this.id = id;
		this.description = description;
		this.quantity = quantity;
		this.amount = amount;
		this.weight = weight;
		this.shippingCost = shippingCost;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Long getWeight() {
		return weight;
	}

	public void setWeight(Long weight) {
		this.weight = weight;
	}

	public BigDecimal getShippingCost() {
		return shippingCost;
	}

	public void setShippingCost(BigDecimal shippingCost) {
		this.shippingCost = shippingCost;
	}

}
