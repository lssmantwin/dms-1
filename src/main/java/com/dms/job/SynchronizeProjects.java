package com.dms.job;

import com.dms.serializable.LocalDateTimeJacksonSerializable;
import com.dms.service.ProjectCommissionService;
import com.dms.service.impl.JobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * Created by louis.liu on 2017/5/14.
 */
public class SynchronizeProjects {

    @Autowired
    private ProjectCommissionService projectService;
    private static final Logger LOGGER = LoggerFactory.getLogger(SynchronizeProjects.class);

    public void synchronizeProjects() {
        LOGGER.info("=====synchronizeProjects Start " );
        projectService.sychronzieProejcts();
        LOGGER.info("=====synchronizeProjects Finish " );
    }
}
