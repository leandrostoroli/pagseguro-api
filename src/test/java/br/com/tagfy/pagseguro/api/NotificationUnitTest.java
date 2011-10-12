package br.com.tagfy.pagseguro.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import br.com.tagfy.pagseguro.api.model.Item;
import br.com.tagfy.pagseguro.api.model.Transaction;
import br.com.tagfy.pagseguro.api.request.NotificationRequest;
import br.com.tagfy.pagseguro.api.request.RequestBuilder;
import br.com.tagfy.pagseguro.api.utils.Get;
import br.com.tagfy.pagseguro.api.utils.XmlUtils;

public class NotificationUnitTest {
	
	private NotificationRequest mockNotificationRequest;
	
	private Get mockGet;
	
	private RequestBuilder mockRequestBuilder;
	
	private Transaction mockTransaction;
	
	@Before
	public void setup() throws Exception {
		String xml = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\"?> <transaction> <date>2011-02-10T16:13:41.000-03:00</date> <code>9E884542-81B3-4419-9A75-BCC6FB495EF1</code> <reference>REF1234</reference> <type>1</type> <status>3</status> <paymentMethod> <type>1</type> <code>101</code> </paymentMethod> <grossAmount>49900.00</grossAmount> <discountAmount>0.00</discountAmount> <feeAmount>0.00</feeAmount> <netAmount>49900.00</netAmount> <extraAmount>0.00</extraAmount> <installmentCount>1</installmentCount> <itemCount>2</itemCount> <items> <item> <id>0001</id> <description>Notebook Prata</description> <quantity>1</quantity> <amount>24300.00</amount> </item> <item> <id>0002</id> <description>Notebook Rosa</description> <quantity>1</quantity> <amount>25600.00</amount> </item> </items> <sender> <name>José Comprador</name> <email>comprador@uol.com.br</email> <phone> <areaCode>11</areaCode> <number>56273440</number> </phone> </sender> <shipping> <address> <street>Av. Brig. Faria Lima</street> <number>1384</number> <complement>5o andar</complement> <district>Jardim Paulistano</district> <postalCode>01452002</postalCode> <city>Sao Paulo</city> <state>SP</state> <country>BRA</country> </address> <type>1</type> <cost>21.50</cost> </shipping> </transaction>";
		mockTransaction = XmlUtils.fromXml(xml, Transaction.class);
		mockGet = Mockito.mock(Get.class);
		Mockito.when(mockGet.resource(Transaction.class)).thenReturn(mockTransaction);
		Mockito.when(mockGet.getCode()).thenReturn(200);
		
		mockRequestBuilder = Mockito.mock(RequestBuilder.class);
		Mockito.when(mockRequestBuilder.buildGet()).thenReturn(mockGet);
		
		mockNotificationRequest = Mockito.mock(NotificationRequest.class);
		Mockito.when(mockNotificationRequest.getRequest()).thenReturn(mockRequestBuilder);
	}
	
	@Test
	public void shouldGetTransaction() {
		try {
			Transaction transaction = NotificationService.get(mockNotificationRequest);
			
			assertNotNull(transaction);
			assertNotNull(transaction.getDate());
			assertEquals("10/02/2011 16:13:41", new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(transaction.getDate()));
			assertEquals("9E884542-81B3-4419-9A75-BCC6FB495EF1", transaction.getCode());
			assertEquals("REF1234", transaction.getReference());
			assertEquals(new Integer(1), transaction.getTransactionType());
			assertEquals(new Integer(3), transaction.getTransactionStatus());
			assertNotNull(transaction.getPaymentMethod());
			assertEquals(new Integer(1), transaction.getPaymentMethod().getType());
			assertEquals(new Integer(101), transaction.getPaymentMethod().getCode());
			assertEquals(new BigDecimal("49900.00"), transaction.getGrossAmount());
			assertEquals(new BigDecimal("0.00"), transaction.getDiscountAmount());
			assertEquals(new BigDecimal("0.00"), transaction.getFeeAmount());
			assertEquals(new BigDecimal("49900.00"), transaction.getNetAmount());
			assertEquals(new BigDecimal("0.00"), transaction.getExtraAmount());
			assertEquals(new Integer(1), transaction.getInstallmentCount());
			
			assertEquals(2, transaction.getItems().size());
			
			Item item1 = transaction.getItems().get(0);
			assertEquals("0001", item1.getId());
			assertEquals("Notebook Prata", item1.getDescription());
			assertEquals(new Integer(1), item1.getQuantity());
			assertEquals(new BigDecimal("24300.00"), item1.getAmount());

			Item item2 = transaction.getItems().get(1);
			assertEquals("0002", item2.getId());
			assertEquals("Notebook Rosa", item2.getDescription());
			assertEquals(new Integer(1), item2.getQuantity());
			assertEquals(new BigDecimal("25600.00"), item2.getAmount());
			
			assertNotNull(transaction.getSender());
			assertEquals("José Comprador", transaction.getSender().getName());
			assertEquals("comprador@uol.com.br", transaction.getSender().getEmail());
			assertNotNull(transaction.getSender().getPhone());
			assertEquals("11", transaction.getSender().getPhone().getAreaCode());
			assertEquals("56273440", transaction.getSender().getPhone().getNumber());
			
			assertNotNull(transaction.getShipping());
			assertEquals(new Integer(1), transaction.getShipping().getType());
			assertEquals(new BigDecimal("21.50"), transaction.getShipping().getCost());
			
			assertNotNull(transaction.getShipping().getAddress());
			assertEquals("Av. Brig. Faria Lima", transaction.getShipping().getAddress().getStreet());
			assertEquals("1384", transaction.getShipping().getAddress().getNumber());
			assertEquals("5o andar", transaction.getShipping().getAddress().getComplement());
			assertEquals("Jardim Paulistano", transaction.getShipping().getAddress().getDistrict());
			assertEquals("01452002", transaction.getShipping().getAddress().getPostalCode());
			assertEquals("Sao Paulo", transaction.getShipping().getAddress().getCity());
			assertEquals("SP", transaction.getShipping().getAddress().getState());
			assertEquals("BRA", transaction.getShipping().getAddress().getCountry());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void shouldParseTransactionXml() {
		String xml = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\"?> <transaction> <date>2011-02-10T16:13:41.000-03:00</date> <code>9E884542-81B3-4419-9A75-BCC6FB495EF1</code> <reference>REF1234</reference> <type>1</type> <status>3</status> <paymentMethod> <type>1</type> <code>101</code> </paymentMethod> <grossAmount>49900.00</grossAmount> <discountAmount>0.00</discountAmount> <feeAmount>0.00</feeAmount> <netAmount>49900.00</netAmount> <extraAmount>0.00</extraAmount> <installmentCount>1</installmentCount> <itemCount>2</itemCount> <items> <item> <id>0001</id> <description>Notebook Prata</description> <quantity>1</quantity> <amount>24300.00</amount> </item> <item> <id>0002</id> <description>Notebook Rosa</description> <quantity>1</quantity> <amount>25600.00</amount> </item> </items> <sender> <name>José Comprador</name> <email>comprador@uol.com.br</email> <phone> <areaCode>11</areaCode> <number>56273440</number> </phone> </sender> <shipping> <address> <street>Av. Brig. Faria Lima</street> <number>1384</number> <complement>5o andar</complement> <district>Jardim Paulistano</district> <postalCode>01452002</postalCode> <city>Sao Paulo</city> <state>SP</state> <country>BRA</country> </address> <type>1</type> <cost>21.50</cost> </shipping> </transaction>";
		try {
			Transaction transaction = XmlUtils.fromXml(xml, Transaction.class);
			
			assertNotNull(transaction);
			assertNotNull(transaction.getDate());
			assertEquals("9E884542-81B3-4419-9A75-BCC6FB495EF1", transaction.getCode());
			assertEquals("REF1234", transaction.getReference());
			assertEquals(new Integer(1), transaction.getTransactionType());
			assertEquals(new Integer(3), transaction.getTransactionStatus());
			assertNotNull(transaction.getPaymentMethod());
			assertEquals(new Integer(1), transaction.getPaymentMethod().getType());
			assertEquals(new Integer(101), transaction.getPaymentMethod().getCode());
			assertEquals(new BigDecimal("49900.00"), transaction.getGrossAmount());
			assertEquals(new BigDecimal("0.00"), transaction.getDiscountAmount());
			assertEquals(new BigDecimal("0.00"), transaction.getFeeAmount());
			assertEquals(new BigDecimal("49900.00"), transaction.getNetAmount());
			assertEquals(new BigDecimal("0.00"), transaction.getExtraAmount());
			assertEquals(new Integer(1), transaction.getInstallmentCount());
			
			assertEquals(2, transaction.getItems().size());
			
			Item item1 = transaction.getItems().get(0);
			assertEquals("0001", item1.getId());
			assertEquals("Notebook Prata", item1.getDescription());
			assertEquals(new Integer(1), item1.getQuantity());
			assertEquals(new BigDecimal("24300.00"), item1.getAmount());

			Item item2 = transaction.getItems().get(1);
			assertEquals("0002", item2.getId());
			assertEquals("Notebook Rosa", item2.getDescription());
			assertEquals(new Integer(1), item2.getQuantity());
			assertEquals(new BigDecimal("25600.00"), item2.getAmount());
			
			assertNotNull(transaction.getSender());
			assertEquals("José Comprador", transaction.getSender().getName());
			assertEquals("comprador@uol.com.br", transaction.getSender().getEmail());
			assertNotNull(transaction.getSender().getPhone());
			assertEquals("11", transaction.getSender().getPhone().getAreaCode());
			assertEquals("56273440", transaction.getSender().getPhone().getNumber());
			
			assertNotNull(transaction.getShipping());
			assertEquals(new Integer(1), transaction.getShipping().getType());
			assertEquals(new BigDecimal("21.50"), transaction.getShipping().getCost());
			
			assertNotNull(transaction.getShipping().getAddress());
			assertEquals("Av. Brig. Faria Lima", transaction.getShipping().getAddress().getStreet());
			assertEquals("1384", transaction.getShipping().getAddress().getNumber());
			assertEquals("5o andar", transaction.getShipping().getAddress().getComplement());
			assertEquals("Jardim Paulistano", transaction.getShipping().getAddress().getDistrict());
			assertEquals("01452002", transaction.getShipping().getAddress().getPostalCode());
			assertEquals("Sao Paulo", transaction.getShipping().getAddress().getCity());
			assertEquals("SP", transaction.getShipping().getAddress().getState());
			assertEquals("BRA", transaction.getShipping().getAddress().getCountry());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void shouldGetErrorCode() {
		Mockito.when(mockGet.getCode()).thenReturn(500);
		Exception exc = null;
		try {
			NotificationService.get(mockNotificationRequest);
		} catch (Exception e) {
			e.printStackTrace();
			exc = e;
		}
		assertNotNull(exc);
	}
	
}
