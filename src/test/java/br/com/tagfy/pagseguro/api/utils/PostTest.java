package br.com.tagfy.pagseguro.api.utils;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import br.com.tagfy.pagseguro.api.PaymentBuilder;
import br.com.tagfy.pagseguro.api.model.Payment;
import br.com.tagfy.pagseguro.api.model.PaymentOrderCode;
import br.com.tagfy.pagseguro.api.model.Shipping;

public class PostTest {
	
	@Test
	public void shouldDoPost() {
		try {
			Payment payment = new PaymentBuilder()
				.shipping(Shipping.SEDEX, 
						"Rua do Boqueirão", "185", "AP 93D", "Jd. da Saúde", "04293000", 
					"São Paulo", "SP",  "BRA", BigDecimal.ZERO)
				.sender("João da Silva", "joao@gmail.com", "11", "99999999")
				.currency("BRL")
				.reference("RF1234")
				.item("1", "Descrição 1", 1, new BigDecimal("150.00"), 1L, new BigDecimal("10.00"))
				.build();
			
			PaymentOrderCode order = Post.at("https://ws.pagseguro.uol.com.br/v2/checkout?email=leandro.storoli@gmail.com&token=C918CC2E0BC144CB8C6F8AFD44D8E1DC")
				.contentType("application/xml; charset=ISO-8859-1")
				.encoding("ISO-8859-1")
				.accept("application/xml")
				.param(payment)
				.post()
				.resource(PaymentOrderCode.class);
			
			assertNotNull(order);
			assertNotNull(order.getCode());
			assertFalse(order.getCode().isEmpty());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
