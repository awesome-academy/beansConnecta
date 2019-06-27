package vn.sun.services.client;

import java.io.Serializable;
import java.util.List;

import vn.sun.entities.JobType;

public interface JobTypeServices {
	
	List<JobType> loadJobTypes();
	
	JobType findById(Serializable key);

	JobType saveOrUpdate(JobType jobType);

	void delete(JobType jobType);
	
}
