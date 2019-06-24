package vn.sun.services.client;

import java.util.List;

import vn.sun.entities.JobTypes;
import vn.sun.entities.Users;

public interface JobTypesServices {
	public List<JobTypes> loadJobTypes();
	
}
