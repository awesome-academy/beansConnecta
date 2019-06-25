package vn.sun.services.client.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import vn.sun.DAO.client.CandidatesDAO;
import vn.sun.entities.Candidates;
import vn.sun.services.client.CandidatesServices;

public class CandidatesServicesImpl implements CandidatesServices {
	private static final Logger logger = Logger.getLogger(CandidatesServicesImpl.class);
	
	@Autowired
	private CandidatesDAO candidatesDAO;

	public CandidatesDAO getCandidatesDAO() {
		return candidatesDAO;
	}

	public void setCandidatesDAO(CandidatesDAO candidatesDAO) {
		this.candidatesDAO = candidatesDAO;
	}

	@Override
	public List<Candidates> loadCandidates() {
		try {
			return candidatesDAO.loadEntities();
		} catch (Exception e) {
			logger.error("Cant load candidadates due to errors");
			return null;
		}
	}
	
	
}
