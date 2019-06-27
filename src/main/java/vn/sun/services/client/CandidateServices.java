package vn.sun.services.client;

import java.util.List;

import vn.sun.entities.Candidate;

public interface CandidateServices {
	public List<Candidate> loadCandidates();
	public void createCandidate();
}
