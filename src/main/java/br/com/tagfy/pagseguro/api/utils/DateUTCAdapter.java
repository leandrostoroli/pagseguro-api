package br.com.tagfy.pagseguro.api.utils;

import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class DateUTCAdapter extends XmlAdapter<String, Date> {

	@Override
	public String marshal(Date date) throws Exception {
		return DateParserUTC.format(date);
	}

	@Override
	public Date unmarshal(String dateStr) throws Exception {
		return DateParserUTC.parse(dateStr);
	}

}
