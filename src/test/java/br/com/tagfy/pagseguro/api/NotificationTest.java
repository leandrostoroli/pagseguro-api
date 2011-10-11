package br.com.tagfy.pagseguro.api;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import br.com.tagfy.pagseguro.api.conf.NotificationConfig;
import br.com.tagfy.pagseguro.api.model.Transaction;

public class NotificationTest {
	
	private NotificationService service;
	
	@Before
	public void setup() {
		service = new NotificationService(new NotificationConfig() {
			
			public String getUrl() {
				return "https://ws.pagseguro.uol.com.br/v2/transactions/notifications";
			}
			
			public String getToken() {
				return "C918CC2E0BC144CB8C6F8AFD44D8E1DC";
			}
			
			public String getEmail() {
				return "leandro.storoli@gmail.com";
			}
		});
	}
	
	@Test
	public void shouldGetTransaction() {
		try {
			Transaction transaction = service.get("766B9C-AD4B044B04DA-77742F5FA653-E1AB24");
			assertNotNull(transaction);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

}
