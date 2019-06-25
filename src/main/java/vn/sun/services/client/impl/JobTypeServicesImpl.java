package vn.sun.services.client.impl;

import java.io.Serializable;
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

	@Override
	public JobType findById(Serializable key) {
		try {
			JobType result_job_type = JobTypeDAO.findById(key);
			if (result_job_type != null) return result_job_type;
			logger.error("job type is null");
			return null;
		}
		catch (DataIntegrityViolationException e) {
			logger.error("Cant not find job type");
			return null;
		}
	}

	@Override
	public JobType saveOrUpdate(JobType jobType) {
		try {
			JobTypeDAO.saveOrUpdate(jobType);
		} catch (DataIntegrityViolationException e) {
			logger.error("Cant save or update jobtypes");
			throw e;
		}
		return jobType;
	}

	@Override
	public void delete(JobType jobType) {
		try {
			JobTypeDAO.delete(jobType);
		} catch (DataIntegrityViolationException e) {
			logger.error("Cant delete this job types");
			throw e;
		}
	}

}
