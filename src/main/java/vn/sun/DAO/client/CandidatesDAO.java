package vn.sun.DAO.client;

import java.util.List;
import vn.sun.entities.Candidates;

public interface CandidatesDAO extends BaseDAO<Integer, Candidates> {
	List<Candidates> loadEntities();
}
