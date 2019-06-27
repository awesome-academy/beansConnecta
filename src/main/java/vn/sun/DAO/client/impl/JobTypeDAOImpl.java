package vn.sun.DAO.client.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.LockMode;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

import vn.sun.DAO.client.AbstractBaseDAO;
import vn.sun.DAO.client.JobTypeDAO;
import vn.sun.entities.JobType;

@Repository
public class JobTypeDAOImpl extends AbstractBaseDAO<Serializable, JobType> implements JobTypeDAO {
	private static final Logger logger = Logger.getLogger(JobTypeDAOImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<JobType> loadEntities() {
		logger.info("loading Job Types");
		return getSession().createQuery("from JobType").getResultList();
	}

}
