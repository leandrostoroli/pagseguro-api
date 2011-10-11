package br.com.tagfy.pagseguro.api.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="checkout")
@XmlAccessorType(XmlAccessType.FIELD)
public class Payment {
	
	// Only accept BR for while	
	private String currency;
	
	// Items on Shopping Cart
	@XmlElementWrapper(name="items")
    @XmlElement(name="item")
	private List<Item> items;
	
	// Custom code for id payment	
	private String reference;
	
	// Who is paying the bill	
	private Sender sender;
	
	// Where to ship	
	private Shipping shipping;
	
	public Payment() {
		items = new ArrayList<Item>();
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public Sender getSender() {
		return sender;
	}

	public void setSender(Sender sender) {
		this.sender = sender;
	}

	public Shipping getShipping() {
		return shipping;
	}

	public void setShipping(Shipping shipping) {
		this.shipping = shipping;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

}
