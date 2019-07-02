package vn.sun.DAO.client;

import java.util.List;

import vn.sun.entities.Job;

public interface JobDAO extends BaseDAO<Integer, Job> {
	List<Job> loadEntities();

	List<Job> query(int firstResult, int lastResult);
	
	Long countAll();
}
