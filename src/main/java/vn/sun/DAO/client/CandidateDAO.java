package vn.sun.DAO.client;

import java.util.List;
import vn.sun.entities.Candidate;

public interface CandidateDAO extends BaseDAO<Integer, Candidate> {
	List<Candidate> loadEntities();
}
