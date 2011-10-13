package br.com.tagfy.pagseguro.api;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpStatus;

import br.com.tagfy.pagseguro.api.model.Transaction;
import br.com.tagfy.pagseguro.api.request.RequestBuilder;
import br.com.tagfy.pagseguro.api.request.TransactionDetailRequest;
import br.com.tagfy.pagseguro.api.request.TransactionSearchRequest;
import br.com.tagfy.pagseguro.api.utils.Get;

public class TransactionService {
	
	public static Transaction detail(TransactionDetailRequest transactionDetailRequest) throws Exception {
		RequestBuilder requestBuilder = transactionDetailRequest.getRequest();
		Get get = requestBuilder.buildGet();
		get.get();
		
		if (get.getCode() == HttpStatus.SC_OK) {
			return get.resource(Transaction.class);
		} else {
			throw new Exception(get.getCode()+" - "+get.getReason());
		}
	}
	
	@SuppressWarnings("unchecked")
	public static List<Transaction> search(TransactionSearchRequest transactionSearchRequest) throws Exception {
		RequestBuilder requestBuilder = transactionSearchRequest.getRequest();
		Get get = requestBuilder.buildGet();
		get.get();
		
		if (get.getCode() == HttpStatus.SC_OK) {
			return get.resource(new ArrayList<Transaction>().getClass());
		} else {
			throw new Exception(get.getCode()+" - "+get.getReason());
		}
	}
	
	@SuppressWarnings("unchecked")
	public static List<Transaction> abandoned(TransactionSearchRequest transactionSearchRequest) throws Exception {
		RequestBuilder requestBuilder = transactionSearchRequest.getRequest();
		Get get = requestBuilder.buildGet();
		get.get();
		
		if (get.getCode() == HttpStatus.SC_OK) {
			return get.resource(new ArrayList<Transaction>().getClass());
		} else {
			throw new Exception(get.getCode()+" - "+get.getReason());
		}
	}

}
