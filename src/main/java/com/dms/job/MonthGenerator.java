package com.dms.job;

import com.dms.service.impl.JobService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class MonthGenerator {

	@Autowired
	private JobService jobService;

	public void generate() {
		jobService.addCurrentMonth();
	}

}
