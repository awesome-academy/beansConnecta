package vn.sun.services.client;

import java.io.Serializable;
import java.util.List;

import vn.sun.entities.Job;

public interface JobServices {
	
	List<Job> loadJobs(int firstResult, int lastResult);
	
	Job findById(Serializable key);

	Job saveOrUpdate(Job jobType);

	void delete(Job jobType);
	
	Long countAll();
	
}
