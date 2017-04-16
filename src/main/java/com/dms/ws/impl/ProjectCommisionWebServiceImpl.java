package com.dms.ws.impl;

import com.dms.service.ProjectCommisionService;
import com.dms.ws.ProjectCommisionWebService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("employeeWebService")
public class ProjectCommisionWebServiceImpl implements ProjectCommisionWebService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProjectCommisionWebServiceImpl.class);

	@Autowired
	private ProjectCommisionService projectCommisionService;



}
