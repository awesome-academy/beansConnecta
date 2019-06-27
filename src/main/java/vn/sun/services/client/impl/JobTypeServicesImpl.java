package vn.sun.services.client.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import vn.sun.DAO.client.JobTypeDAO;
import vn.sun.entities.JobType;
import vn.sun.services.client.JobTypeServices;

public class JobTypeServicesImpl implements JobTypeServices {
	private static final Logger logger = Logger.getLogger(JobTypeServicesImpl.class);

	@Autowired
	private JobTypeDAO JobTypeDAO;

	public void setJobTypeDAO(JobTypeDAO jobTypeDAO) {
		this.JobTypeDAO = jobTypeDAO;
	}

	@Override
	public List<JobType> loadJobTypes() {
		try {
			return JobTypeDAO.loadEntities();
		} catch (Exception e) {
			logger.error("has error by loadJobType method");
			return null;
		}
	}

}
