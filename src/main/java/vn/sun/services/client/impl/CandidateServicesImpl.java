package vn.sun.services.client.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import vn.sun.DAO.client.CandidateDAO;
import vn.sun.entities.Candidate;
import vn.sun.services.client.CandidateServices;

public class CandidateServicesImpl implements CandidateServices {
	private static final Logger logger = Logger.getLogger(CandidateServicesImpl.class);
	
	@Autowired
	private CandidateDAO candidateDAO;

	public void setCandidatesDAO(CandidateDAO candidateDAO) {
		this.candidateDAO = candidateDAO;
	}

	@Override
	public List<Candidate> loadCandidates() {
		try {
			return candidateDAO.loadEntities();
		} catch (Exception e) {
			logger.error("Cant load candidadates due to errors");
			return null;
		}
	}

	@Override
	public void createCandidate() {
		Candidate candidate = new Candidate();
		try {
			candidateDAO.createEntity(candidate);
		} catch (DataIntegrityViolationException e) {
			logger.error("Cant save");
		}
		
	}
	
	
}