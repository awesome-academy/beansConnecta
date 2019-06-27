package vn.sun.services.client.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import vn.sun.DAO.client.JobTypeDAO;
import vn.sun.entities.JobType;
import vn.sun.services.client.JobTypeServices;

public class JobTypeServicesImpl implements JobTypeServices {
	private static final Logger logger = Logger.getLogger(JobTypeServicesImpl.class);

	@Autowired
	private JobTypeDAO JobTypeDAO;

	public void setJobTypesDAO(JobTypeDAO jobTypeDAO) {
		this.JobTypeDAO = jobTypeDAO;
	}

	@Override
	public List<JobType> loadJobTypes() {
		try {
			return JobTypeDAO.loadEntities();
		} catch (Exception e) {
			logger.error("has error by loadJobTypes method");
			return null;
		}
	}

	@Override
	public void createJobtype() {
		JobType jtype = new JobType();
		try {
			JobTypeDAO.createEntity(jtype);
		} catch (DataIntegrityViolationException e) {
			logger.error("Cant save");
		}
	}

}
