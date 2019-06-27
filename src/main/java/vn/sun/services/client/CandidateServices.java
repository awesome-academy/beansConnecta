package vn.sun.services.client;

import java.io.Serializable;
import java.util.List;

import vn.sun.entities.Candidate;

public interface CandidateServices {
	
	List<Candidate> loadCandidates();

	Candidate saveOrUpdate(Candidate candidate);

	void delete(Candidate candidate);

	Candidate findById(Serializable key);

}
