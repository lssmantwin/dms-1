package com.dms.service.impl;

import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dms.dao.JobDao;
import com.dms.domain.Month;

@Service
public class JobService {

	private static final Logger LOGGER = LoggerFactory.getLogger(JobService.class);

	@Autowired
	private JobDao jobDao;

	public void addCurrentMonth() {
		LOGGER.info("==== add last month ====");
		LocalDateTime now = LocalDateTime.now().minusMonths(1);
		Month month = new Month();
		month.setValue(now.toString("yyyyMM"));
		month.setText(now.toString("yyyy年MM月"));
		jobDao.addCurrentMonth(month);
	}

}
