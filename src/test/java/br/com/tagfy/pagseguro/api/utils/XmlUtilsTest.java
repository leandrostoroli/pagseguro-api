package br.com.tagfy.pagseguro.api.utils;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Test;

import br.com.tagfy.pagseguro.api.model.PaymentError;
import br.com.tagfy.pagseguro.api.model.PaymentOrderCode;
import br.com.tagfy.pagseguro.api.utils.XmlUtils;

public class XmlUtilsTest {
	
	@Test
	public void shouldParseCheckout() {
		String xml = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\"?><checkout><code>C4412A888585B950048FBFA082895AC7</code><date>2011-10-08T12:47:32.000-03:00</date></checkout>";
		try {
			PaymentOrderCode orderCode = XmlUtils.fromXml(xml, PaymentOrderCode.class);
			assertNotNull(orderCode);
			assertNotNull(orderCode.getCode());
			assertEquals("C4412A888585B950048FBFA082895AC7", orderCode.getCode());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	@Test
	public void shouldParseErrorsOnCheckout() {
		String xml = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\"?><errors><error><code>11004</code><message>Currency is required.</message></error></errors>";
		try {
			PaymentError error = XmlUtils.fromXml(xml, PaymentError.class);
			assertNotNull(error);
			assertNotNull(error.getErrors());
			assertEquals(1, error.getErrors().size());
			assertEquals("11004", error.getErrors().get(0).getCode());
			assertEquals("Currency is required.", error.getErrors().get(0).getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

}
