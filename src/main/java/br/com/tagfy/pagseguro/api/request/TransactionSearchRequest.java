package br.com.tagfy.pagseguro.api.request;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import br.com.tagfy.pagseguro.api.conf.Config;

public class TransactionSearchRequest {
	
	private Date initialDate;
	
	private Date finalDate;
	
	private int page;
	
	private int maxPageResults;
	
	private Config config;
	
	private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm"; // "2011-04-01T08:30" 
	
	public TransactionSearchRequest(Config config) {
		Calendar calendar = Calendar.getInstance();
		finalDate = calendar.getTime();
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		initialDate = calendar.getTime();
		page = 1;
		maxPageResults = 10;
		this.config = config;
	}
	
	public TransactionSearchRequest(Date initialDate, Date finalDate, Config config) {
		page = 1;
		maxPageResults = 10;
		this.config = config;
		this.initialDate = initialDate;
		this.finalDate = finalDate;
	}

	public TransactionSearchRequest(Date initialDate, Date finalDate, int page,
			int maxPageResults, Config config) {
		super();
		this.initialDate = initialDate;
		this.finalDate = finalDate;
		this.page = page;
		this.maxPageResults = maxPageResults;
		this.config = config;
	}
	
	public RequestBuilder getRequest() {
		SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
		String uri = config.getUrl()+"?email="+config.getEmail()+"&token="+config.getToken()
			+"&initialDate="+dateFormat.format(initialDate)
			+"&finalDate="+dateFormat.format(finalDate)
			+"&page="+page
			+"&maxPageResults="+maxPageResults;
		return RequestBuilder.at(uri)
			.encoding(config.getEncoding());
	}

	public Config getConfig() {
		return config;
	}

}
