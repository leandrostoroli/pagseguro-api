package br.com.tagfy.pagseguro.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import br.com.tagfy.pagseguro.api.conf.Config;
import br.com.tagfy.pagseguro.api.model.Payment;
import br.com.tagfy.pagseguro.api.model.PaymentOrder;
import br.com.tagfy.pagseguro.api.model.Shipping;
import br.com.tagfy.pagseguro.api.request.PaymentRequest;

public class PaymentUnitTest {
	
	private Payment payment;
	
	private PaymentRequest request;
	
	@Before
	public void setup() {
		payment = new PaymentBuilder()
			.shipping(Shipping.SEDEX, 
					"Rua do Boqueirão", "185", "AP 93D", "Jd. da Saúde", "04293000", 
				"São Paulo", "SP",  "BRA", BigDecimal.ZERO)
			.sender("João da Silva", "joao@gmail.com", "11", "99999999")
			.currency("BRL")
			.reference("RF1234")
			.item("1", "Descrição 1", 1, new BigDecimal("150.00"), 1L, new BigDecimal("10.00"))
			.build();
		
		request = new PaymentRequest(payment, new Config() {
			public String getUrl() {
				return "https://ws.pagseguro.uol.com.br/v2/checkout";
			}
			
			public String getToken() {
				return "C918CC2E0BC144CB8C6F8AFD44D8E1DC";
			}
			
			public String getEncoding() {
				return "ISO-8859-1";
			}
			
			public String getEmail() {
				return "leandro.storoli@gmail.com";
			}
		});
		
	}
	
	@Test
	public void shouldPay() {
		try {
			PaymentOrder orderCode = PaymentService.pay(request);
			
			assertNotNull(orderCode);
			assertNotNull(orderCode.getOrderCode());
			assertNotNull(orderCode.getOrderCode().getDate());
			assertFalse(orderCode.getOrderCode().getCode().isEmpty());
			assertFalse(orderCode.getOrderCode().getDate().isEmpty());
			System.out.println(orderCode.getOrderCode().getCode());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	@Test
	public void shouldNotPayAndGetErrors() {
		payment.setCurrency("");
		
		try {
			PaymentOrder orderCode = PaymentService.pay(request);
			
			assertNotNull(orderCode);
			assertNotNull(orderCode.getError());
			assertNotNull(orderCode.getError().getErrors());
			assertEquals(1, orderCode.getError().getErrors().size());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

}
