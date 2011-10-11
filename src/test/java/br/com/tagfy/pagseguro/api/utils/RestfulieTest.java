package br.com.tagfy.pagseguro.api.utils;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import br.com.caelum.restfulie.Response;
import br.com.caelum.restfulie.Restfulie;
import br.com.tagfy.pagseguro.api.PaymentBuilder;
import br.com.tagfy.pagseguro.api.model.Payment;
import br.com.tagfy.pagseguro.api.model.PaymentOrderCode;
import br.com.tagfy.pagseguro.api.model.Shipping;

public class RestfulieTest {
	
	@Test
	public void shouldPost() {
		Payment payment = new PaymentBuilder()
			.shipping(Shipping.SEDEX, 
					"Rua do Boqueirão", "185", "AP 93D", "Jd. da Saúde", "04293000", 
				"São Paulo", "SP",  "BRA", BigDecimal.ZERO)
			.sender("João da Silva", "joao@gmail.com", "11", "99999999")
			.currency("BRL")
			.reference("RF1234")
			.item("1", "Descrição 1", 1, new BigDecimal("150.00"), 1L, new BigDecimal("10.00"))
			.build();
		
		Response restfulie = Restfulie.at("https://ws.pagseguro.uol.com.br/v2/checkout?email=leandro.storoli@gmail.com&token=C918CC2E0BC144CB8C6F8AFD44D8E1DC")
		.handling("application/xml")
		.as("application/xml")
		.accept("application/xml")
		.post(payment);

		System.out.println(restfulie.getHeader("Content-type"));
		System.out.println(restfulie.getContent());
		
//		PaymentOrderCode order = Restfulie.at("https://ws.pagseguro.uol.com.br/v2/checkout?email=leandro.storoli@gmail.com&token=C918CC2E0BC144CB8C6F8AFD44D8E1DC")
//			.as("application/xml")
//			.post(payment)
//			.getResource();
		
//		assertNotNull(order);
//		assertNotNull(order.getCode());
//		assertFalse(order.getCode().isEmpty());
	}

}
