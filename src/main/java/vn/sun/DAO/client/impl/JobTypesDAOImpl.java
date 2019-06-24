package vn.sun.DAO.client.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import vn.sun.DAO.client.AbstractBaseDAO;
import vn.sun.DAO.client.JobTypesDAO;
import vn.sun.entities.JobTypes;

@Repository
public class JobTypesDAOImpl extends AbstractBaseDAO<Serializable, JobTypes> implements JobTypesDAO {
	private static final Logger logger = Logger.getLogger(JobTypesDAOImpl.class);
	@SuppressWarnings("unchecked")
	@Override
	public List<JobTypes> loadEntities() {
		logger.info("load jobTypes");
		return getSession().createQuery("from JobTypes").getResultList();
	}

}
