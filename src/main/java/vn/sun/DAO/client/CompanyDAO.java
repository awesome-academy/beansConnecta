package vn.sun.DAO.client;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import vn.sun.entities.Company;
import vn.sun.entities.Job;

public interface CompanyDAO extends BaseDAO<Integer, Company> {
	List<Company> loadEntities();

	List<Job> loadCompanyJobs(Serializable key);

	Map<String,String> loadTopActive();
}
