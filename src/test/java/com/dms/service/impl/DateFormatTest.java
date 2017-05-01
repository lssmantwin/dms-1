package com.dms.service.impl;

import org.joda.time.LocalDateTime;
import org.junit.Assert;
import org.junit.Test;

public class DateFormatTest {

	LocalDateTime time = new LocalDateTime(2000, 01, 01, 01, 01, 01);

	@Test
	public void test2String() {
		String result = time.toString("yyyyMM");
		Assert.assertEquals("200001", result);
	}

	@Test
	public void test2String2() {
		String result = time.toString("yyyy年MM月");
		Assert.assertEquals("2000年01月", result);
	}
}
