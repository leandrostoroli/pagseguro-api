package br.com.tagfy.pagseguro.api;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import br.com.tagfy.pagseguro.api.model.TransactionSearchResult;
import br.com.tagfy.pagseguro.api.model.TransactionSummary;
import br.com.tagfy.pagseguro.api.request.RequestBuilder;
import br.com.tagfy.pagseguro.api.request.TransactionSearchRequest;
import br.com.tagfy.pagseguro.api.utils.Get;
import br.com.tagfy.pagseguro.api.utils.XmlUtils;

public class TransactionSearchUnitTest {
	
	private TransactionSearchRequest mockTransactionSearchRequest;
	
	private Get mockGet;
	
	private RequestBuilder mockRequestBuilder;
	
	private TransactionSearchResult mockTransactionResult;
	
	@Before
	public void setup() throws Exception {
		String xml = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\"?><transactionSearchResult><date>2011-02-16T20:14:35.000-02:00</date> <currentPage>1</currentPage> <resultsInThisPage>10</resultsInThisPage> <totalPages>1</totalPages> <transactions> <transaction> <date>2011-02-05T15:46:12.000-02:00</date> <lastEventDate>2011-02-15T17:39:14.000-03:00</lastEventDate> <code>9E884542-81B3-4419-9A75-BCC6FB495EF1</code> <reference>REF1234</reference> <type>1</type> <status>3</status> <paymentMethod> <type>1</type> </paymentMethod> <grossAmount>49900.00</grossAmount> <discountAmount>0.00</discountAmount> <feeAmount>0.00</feeAmount> <netAmount>49900.00</netAmount> <extraAmount>0.00</extraAmount> </transaction> <transaction> <date>2011-02-07T18:57:52.000-02:00</date> <lastEventDate>2011-02-14T21:37:24.000-03:00</lastEventDate> <code>2FB07A22-68FF-4F83-A356-24153A0C05E1</code> <reference>REF5678</reference> <type>3</type> <status>4</status> <paymentMethod> <type>3</type> </paymentMethod> <grossAmount>26900.00</grossAmount> <discountAmount>0.00</discountAmount> <feeAmount>0.00</feeAmount> <netAmount>26900.00</netAmount> <extraAmount>0.00</extraAmount> </transaction> </transactions> </transactionSearchResult>";
		mockTransactionResult = XmlUtils.fromXml(xml, TransactionSearchResult.class);
		mockGet = Mockito.mock(Get.class);
		Mockito.when(mockGet.resource(TransactionSearchResult.class)).thenReturn(mockTransactionResult);
		Mockito.when(mockGet.getCode()).thenReturn(200);
		
		mockRequestBuilder = Mockito.mock(RequestBuilder.class);
		Mockito.when(mockRequestBuilder.buildGet()).thenReturn(mockGet);
		
		mockTransactionSearchRequest = Mockito.mock(TransactionSearchRequest.class);
		Mockito.when(mockTransactionSearchRequest.getRequest()).thenReturn(mockRequestBuilder);
	}
	
	@Test
	public void shouldGetTransactions() {
		try {
			mockTransactionResult = TransactionService.search(mockTransactionSearchRequest);
			
			assertNotNull(mockTransactionResult.getDate());
			assertEquals(1, mockTransactionResult.getCurrentPage());
			assertEquals(10, mockTransactionResult.getResultsInThisPage());
			assertEquals(1, mockTransactionResult.getTotalPages());
			assertEquals(2, mockTransactionResult.getTransactions().size());
			
			TransactionSummary transaction1 = mockTransactionResult.getTransactions().get(0);
			assertNotNull(transaction1.getDate());
			assertNotNull(transaction1.getLastEventDate());
			assertEquals("9E884542-81B3-4419-9A75-BCC6FB495EF1", transaction1.getCode());
			assertEquals("REF1234", transaction1.getReference());
			assertEquals(new Integer(1), transaction1.getTransactionType());
			assertEquals(new Integer(3), transaction1.getTransactionStatus());
			assertNotNull(transaction1.getPaymentMethod());
			assertEquals(new Integer(1), transaction1.getPaymentMethod().getType());
			assertEquals(new BigDecimal("49900.00"), transaction1.getGrossAmount());
			assertEquals(new BigDecimal("0.00"), transaction1.getDiscountAmount());
			assertEquals(new BigDecimal("0.00"), transaction1.getFeeAmount());
			assertEquals(new BigDecimal("49900.00"), transaction1.getNetAmount());
			assertEquals(new BigDecimal("0.00"), transaction1.getExtraAmount());

			TransactionSummary transaction2 = mockTransactionResult.getTransactions().get(1);
			assertNotNull(transaction2.getDate());
			assertNotNull(transaction2.getLastEventDate());
			assertEquals("2FB07A22-68FF-4F83-A356-24153A0C05E1", transaction2.getCode());
			assertEquals("REF5678", transaction2.getReference());
			assertEquals(new Integer(3), transaction2.getTransactionType());
			assertEquals(new Integer(4), transaction2.getTransactionStatus());
			assertNotNull(transaction2.getPaymentMethod());
			assertEquals(new Integer(3), transaction2.getPaymentMethod().getType());
			assertEquals(new BigDecimal("26900.00"), transaction2.getGrossAmount());
			assertEquals(new BigDecimal("0.00"), transaction2.getDiscountAmount());
			assertEquals(new BigDecimal("0.00"), transaction2.getFeeAmount());
			assertEquals(new BigDecimal("26900.00"), transaction2.getNetAmount());
			assertEquals(new BigDecimal("0.00"), transaction2.getExtraAmount());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
		
	}

	@Test
	public void shouldGetError() {
		Mockito.when(mockGet.getCode()).thenReturn(500);
		Exception exc = null;
		try {
			TransactionService.search(mockTransactionSearchRequest);
		} catch (Exception e) {
			e.printStackTrace();
			exc = e;
		}
		assertNotNull(exc);
	}
}
