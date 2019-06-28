package vn.sun.DAO.client.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
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

	@Override
	public Map<String,String> loadTopActive() {
		logger.info("load TopActive");

		String sqlString = "SELECT c.name , COUNT(companyId) "
				+ "FROM jobs AS j INNER JOIN companies AS c " + "ON j.companyId=c.id "
				+ "GROUP BY companyId ORDER BY COUNT(companyId) DESC LIMIT 3";
		
		List<Object[]> queryResutl = (List<Object[]>) getSession().createNativeQuery(sqlString).getResultList();
		Map <String, String> topActiveCompanies = new HashMap<String, String>();
		for (Object[] o : queryResutl) {
			 topActiveCompanies.put(o[0].toString() , o[1].toString());
		}
		
		return topActiveCompanies;
	}

}
