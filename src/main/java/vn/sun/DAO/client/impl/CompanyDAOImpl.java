package vn.sun.DAO.client.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;

import vn.sun.DAO.client.AbstractBaseDAO;
import vn.sun.DAO.client.CompanyDAO;
import vn.sun.entities.Company;
import vn.sun.entities.Job;

@Repository
public class CompanyDAOImpl extends AbstractBaseDAO<Serializable, Company> implements CompanyDAO {
	private static final Logger logger = Logger.getLogger(CompanyDAOImpl.class);

	@SuppressWarnings("unchecked")
	
	public List<Company> loadEntities() {
		logger.info("load companies");
		return getSession().createQuery("from Company").getResultList();
	}

	public List<Job> loadCompanyJobs(Serializable key) {
		logger.info("load companyJobs");
		Company company = (Company) getSession().createQuery("from Company where id=:id").setParameter("id", key)
				.uniqueResult();
		Hibernate.initialize(company.getJobs());
		return company.getJobs();
	}

}
