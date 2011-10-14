package br.com.tagfy.pagseguro.api.utils;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

public class DateParserUTCUnitTest {
	
	@Test
	public void shouldParseDate() {
		try {
			Date date1 = DateParserUTC.parse("2011-02-05T15:46:12.000-02:00");
			Calendar calendar1 = Calendar.getInstance();
			calendar1.setTime(date1);
			
			assertEquals(2011, calendar1.get(Calendar.YEAR));
			assertEquals(1, calendar1.get(Calendar.MONTH));
			assertEquals(5, calendar1.get(Calendar.DAY_OF_MONTH));
			assertEquals(15, calendar1.get(Calendar.HOUR_OF_DAY));
			assertEquals(46, calendar1.get(Calendar.MINUTE));
			assertEquals(12, calendar1.get(Calendar.SECOND));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void shouldFormatDate() {
		try {
			String formattedDate = DateParserUTC.format(DateParserUTC.parse("2011-02-05T15:46:12.000-02:00"));
			assertEquals("2011-02-05T15:46", formattedDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
