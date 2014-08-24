package br.com.tagfy.pagseguro.api;

import java.math.BigDecimal;
import java.util.List;

import br.com.tagfy.pagseguro.api.model.Address;
import br.com.tagfy.pagseguro.api.model.Item;
import br.com.tagfy.pagseguro.api.model.Payment;
import br.com.tagfy.pagseguro.api.model.Phone;
import br.com.tagfy.pagseguro.api.model.Sender;
import br.com.tagfy.pagseguro.api.model.Shipping;

public class PaymentBuilder {
	
	private Payment payment;
	
	public PaymentBuilder() {
		payment = new Payment();
	}
	
	public PaymentBuilder item(Item item) {
		payment.getItems().add(item);
		return this;
	}
	
	public PaymentBuilder item(String id, String description, Integer quantity, BigDecimal amount, 
			Long weight, BigDecimal shippingCost) {
		payment.getItems().add(new Item(id, description, quantity, amount, weight, shippingCost));
		return this;
	}
	
	public PaymentBuilder itens(List<Item> itens) {
		payment.getItems().addAll(itens);
		return this;
	}	
	
	public PaymentBuilder shipping(Shipping shipping) {
		payment.setShipping(shipping);
		return this;
	}
	
	public PaymentBuilder shipping(Integer type, Address address, BigDecimal cost) {
		payment.setShipping(new Shipping(type, address, cost));
		return this;
	}
	
	public PaymentBuilder shipping(Integer type, String street, String number, String complement, 
			String district, String postalCode, String city, String state, String country, BigDecimal cost) {
		payment.setShipping(new Shipping(type, new Address(street, number, complement, district, postalCode, city, state, country), cost));
		return this;
	}
	
	public PaymentBuilder sender(Sender sender) {
		payment.setSender(sender);
		return this;
	}
	
	public PaymentBuilder sender(String name, String email, Phone phone) {
		payment.setSender(new Sender(name, email, phone));
		return this;
	}
	
	public PaymentBuilder sender(String name, String email, String areaCode, String number) {
		payment.setSender(new Sender(name, email, new Phone(areaCode, number)));
		return this;
	}
	
	public PaymentBuilder reference(String reference) {
		payment.setReference(reference);
		return this;
	}
	
	public PaymentBuilder currency(String currency) {
		payment.setCurrency(currency);
		return this;
	}

	public Payment build() {
		return payment;
	}
	
}
