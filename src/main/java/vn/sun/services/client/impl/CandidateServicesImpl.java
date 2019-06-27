package vn.sun.services.client.impl;

import java.io.Serializable;
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

	public void setCandidateDAO(CandidateDAO candidateDAO) {
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
	public Candidate findById(Serializable key) {
		try {
			Candidate result_candidate = candidateDAO.findById(key);
			if (result_candidate != null) return result_candidate;
			logger.error("candidate result is null");
			return null;
		} catch (DataIntegrityViolationException e) {
			logger.error("Cant not find candidate");
			return null;
		}
	}

	@Override
	public Candidate saveOrUpdate(Candidate candidate) {
		try {
			candidateDAO.saveOrUpdate(candidate);
		} catch (DataIntegrityViolationException e) {
			logger.error("Cant not save or update");
			throw e;
		}
		return candidate;
	}

	@Override
	public void delete(Candidate candidate) {
		try {
			candidateDAO.delete(candidate);
		} catch (DataIntegrityViolationException e) {
			logger.error("Cant delete candidate");
			throw e;
		}
	}

}
