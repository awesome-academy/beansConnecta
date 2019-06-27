package vn.sun.services.client;

import java.util.List;

import vn.sun.entities.JobType;

public interface JobTypeServices {
	public List<JobType> loadJobTypes();
	public void createJobtype();
	
}
