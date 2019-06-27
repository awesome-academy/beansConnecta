package vn.sun.services.client;

import java.io.Serializable;
import java.util.List;

import vn.sun.entities.Company;
import vn.sun.entities.Job;

public interface CompanyServices {
	List<Company> loadCompanies();
	
	Company findById(Serializable key);
	
	List<Job> loadCompanyJobs(Serializable key);
	
}
