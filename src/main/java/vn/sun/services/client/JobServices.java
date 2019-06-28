package vn.sun.services.client;

import java.io.Serializable;
import java.util.List;

import vn.sun.entities.Job;

public interface JobServices {
	
	List<Job> loadJobs();
	
	Job findById(Serializable key);

	Job saveOrUpdate(Job jobType);

	void delete(Job jobType);
}
