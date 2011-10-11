package br.com.tagfy.pagseguro.api.utils;

import static org.junit.Assert.*;

import org.junit.Test;

public class GetTest {
	
	@Test
	public void shouldGet() {
		try {
			String content = Get.at("http://google.com")
				.encoding("ISO-8859-1")
				.param("q", "Scarlet Johannson")
				.get()
				.resource(null);
			System.out.println(content);
			assertNotNull(content);
			assertFalse(content.isEmpty());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
