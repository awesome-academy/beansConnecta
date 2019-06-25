package vn.sun.DAO.client.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.LockMode;
import org.springframework.dao.DataIntegrityViolationException;

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

	@Override
	public void createEntity(Candidate candidate) {
		getSession().persist(candidate);
	}

	@Override
	public Candidate findById(int key) {
		Candidate candidate_result = getSession().load(Candidate.class, key, LockMode.OPTIMISTIC);
		if (candidate_result == null) {
			logger.error("Null object");
			return null;
		}
		else return candidate_result;	
	}

}
