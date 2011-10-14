package br.com.tagfy.pagseguro.api.model;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import br.com.tagfy.pagseguro.api.utils.DateUTCAdapter;

@XmlRootElement(name="transactionSearchResult")
@XmlAccessorType(XmlAccessType.FIELD)
public class TransactionSearchResult {
	
	@XmlJavaTypeAdapter(value=DateUTCAdapter.class, type=Date.class)
	private Date date;
	
	private int currentPage;
	
	private int resultsInThisPage;
	
	private int totalPages;
	
	@XmlElementWrapper(name="transactions")
    @XmlElement(name="transaction")
	private List<TransactionSummary> transactions;
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getResultsInThisPage() {
		return resultsInThisPage;
	}

	public void setResultsInThisPage(int resultsInThisPage) {
		this.resultsInThisPage = resultsInThisPage;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public List<TransactionSummary> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<TransactionSummary> transactions) {
		this.transactions = transactions;
	}

}
