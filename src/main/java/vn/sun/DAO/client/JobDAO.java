package vn.sun.DAO.client;

import java.util.List;

import vn.sun.entities.Job;

public interface JobDAO extends BaseDAO<Integer, Job> {
	List<Job> loadEntities();

	List<Job> loadJobs(int firstResult, int maxResult);
	
	Long countJobs(String keyword);
	
	List<Job> search(String keyword,int firstResult, int maxResult);
}
