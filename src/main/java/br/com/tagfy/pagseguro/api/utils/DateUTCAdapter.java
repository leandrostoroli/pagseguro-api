package br.com.tagfy.pagseguro.api.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class DateUTCAdapter extends XmlAdapter<String, Date> {

	@Override
	public String marshal(Date date) throws Exception {
		return new SimpleDateFormat("YYYY-MM-DDThh:mm:ss.sTZD").format(date);
	}

	@Override
	public Date unmarshal(String dateStr) throws Exception {
		return DateParserUTC.parse(dateStr);
	}

}
