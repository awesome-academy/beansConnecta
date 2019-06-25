package vn.sun.DAO.client.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;

import vn.sun.DAO.client.AbstractBaseDAO;
import vn.sun.DAO.client.CandidatesDAO;
import vn.sun.entities.Candidates;

public class CandidatesDAOImpl extends AbstractBaseDAO<Serializable, Candidates> implements CandidatesDAO {

	private static final Logger logger = Logger.getLogger(CandidatesDAOImpl.class);
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Candidates> loadEntities() {
		logger.info("Load candidates");
		return getSession().createQuery("from Candidates").getResultList();
	}

}
