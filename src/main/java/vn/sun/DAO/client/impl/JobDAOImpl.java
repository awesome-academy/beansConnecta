package vn.sun.DAO.client.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import vn.sun.DAO.client.AbstractBaseDAO;
import vn.sun.DAO.client.JobDAO;
import vn.sun.entities.Job;

@Repository
public class JobDAOImpl extends AbstractBaseDAO<Serializable, Job> implements JobDAO {
	
	private static final Logger logger = Logger.getLogger(JobDAOImpl.class);
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Job> loadEntities() {
		logger.info("load jobTypes");
		return getSession().createQuery("from Job").getResultList();
	}

}
