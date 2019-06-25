package vn.sun.DAO.client.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.LockMode;

import vn.sun.DAO.client.AbstractBaseDAO;
import vn.sun.DAO.client.CandidateDAO;
import vn.sun.entities.Candidate;

public class CandidateDAOImpl extends AbstractBaseDAO<Serializable, Candidate> implements CandidateDAO {

	private static final Logger logger = Logger.getLogger(CandidateDAOImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<Candidate> loadEntities() {
		logger.info("Load candidates");
		return getSession().createQuery("from Candidate").getResultList();
	}

}
