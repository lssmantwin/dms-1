package com.dms.security;

import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;

public class Test {

	public static void main(String[] args) {
		System.out.print(LocalDateTime.parse("2017-04-25T00:00:00", DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss")));
	}

}
