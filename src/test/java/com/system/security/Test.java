package com.system.security;

import org.joda.time.LocalDate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {

	public static void main(String[] args) throws ParseException {
		String str = "2017-04-08T00:00:00";
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		String date = sf.format(LocalDate.now().toDate());
		System.out.println(date);

		Date date2 = sf.parse(str);
		String str2 = sf.format(date2);
		System.out.println(str2);
	}

}
