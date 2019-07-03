package vn.sun.services.client.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import vn.sun.DAO.client.JobDAO;
import vn.sun.entities.Job;
import vn.sun.services.client.JobServices;

public class JobServicesImpl implements JobServices {
	private static final Logger logger = Logger.getLogger(JobServicesImpl.class);

	@Autowired
	private JobDAO JobDAO;

	public void setJobDAO(JobDAO jobDAO) {
		this.JobDAO = jobDAO;
	}

	@Override
	public List<Job> loadJobs(int firstResult, int maxResult) {
		try {
			return JobDAO.loadJobs(firstResult, maxResult);
		} catch (Exception e) {
			logger.error("has error by loadJob method");
			return null;
		}
	}

	@Override
	public Job findById(Serializable key) {
		try {
			Job result_job_type = JobDAO.findById(key);
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
	public Job saveOrUpdate(Job job) {
		try {
			JobDAO.saveOrUpdate(job);
		} catch (DataIntegrityViolationException e) {
			logger.error("Cant save or update jobtypes");
			throw e;
		}
		return job;
	}

	@Override
	public void delete(Job job) {
		try {
			JobDAO.delete(job);
		} catch (DataIntegrityViolationException e) {
			logger.error("Cant delete this job types");
			throw e;
		}
	}

	@Override
	public Long countJobs(String keyword) {
		try {
			return JobDAO.countJobs(keyword);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Job> search(String keyword, int firstResult, int maxResult) {
		try {
			if (keyword == "" || keyword == null) {
				return JobDAO.loadJobs(firstResult, maxResult);
			}
			return JobDAO.search(keyword, firstResult, maxResult);
		} catch (Exception e) {
			logger.error("Error on full text search: " + e);
			return null;
		}
	}

}
