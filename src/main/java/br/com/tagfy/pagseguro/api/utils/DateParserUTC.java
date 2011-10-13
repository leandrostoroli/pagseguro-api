package br.com.tagfy.pagseguro.api.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class DateParserUTC {

    public static Date parse(String s) throws ParseException {
        Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        calendar.clear();

        // date format: YYYY-MM-DDThh:mm:ss.sTZD
        String dateWithoutTZ = s.substring(0, 23);
        String timeZone = s.substring(23, 29);

        Calendar calWithoutTZ = new GregorianCalendar();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.S");
        Date date = df.parse(dateWithoutTZ);
        calWithoutTZ.setTimeInMillis(date.getTime());

        calendar.set(Calendar.YEAR, calWithoutTZ.get(Calendar.YEAR));
        calendar.set(Calendar.MONTH, calWithoutTZ.get(Calendar.MONTH));
        calendar.set(Calendar.DAY_OF_MONTH, calWithoutTZ.get(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, calWithoutTZ.get(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calWithoutTZ.get(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calWithoutTZ.get(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND, calWithoutTZ.get(Calendar.MILLISECOND));

//        System.out.println(df.format(calendar.getTime()));
        
        int tzHour = Integer.parseInt(timeZone.substring(1, 3));
        int tzMin = Integer.parseInt(timeZone.substring(4, 6));
        boolean plus = timeZone.substring(0, 1).equals("+");
        if (plus) {
            calendar.add(Calendar.HOUR, -tzHour);
            calendar.add(Calendar.MINUTE, -tzMin);
        } else {
            calendar.add(Calendar.HOUR, tzHour);
            calendar.add(Calendar.MINUTE, tzMin);
        }
        return calendar.getTime();
    }
}