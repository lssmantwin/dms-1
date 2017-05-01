package com.dms.service.impl;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dms.dao.JobDao;
import com.dms.domain.Month;

@Service
public class JobService {

	@Autowired
	private JobDao jobDao;

	public void addCurrentMonth() {
		LocalDateTime now = LocalDateTime.now();
		Month month = new Month();
		month.setValue(now.toString("yyyyMM"));
		month.setText(now.toString("yyyy年MM月"));
		jobDao.addCurrentMonth(month);
	}

}
