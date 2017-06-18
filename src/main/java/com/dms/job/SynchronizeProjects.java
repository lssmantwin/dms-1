package com.dms.job;

import com.dms.service.ProjectCommissionService;
import com.dms.service.impl.JobService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Created by louis.liu on 2017/5/14.
 */
public class SynchronizeProjects {

	@Autowired
	private ProjectCommissionService projectService;

	public void synchronizeProjects() {
		 projectService.sychronzieProejcts();
	}
}
