package vn.sun.DAO.client.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.LockMode;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

import vn.sun.DAO.client.AbstractBaseDAO;
import vn.sun.DAO.client.CompanyDAO;
import vn.sun.entities.Company;

@Repository
public class CompanyDAOImpl extends AbstractBaseDAO<Serializable, Company> implements CompanyDAO {
	private static final Logger logger = Logger.getLogger(CompanyDAOImpl.class);
	@SuppressWarnings("unchecked")
	@Override
	public List<Company> loadEntities() {
		logger.info("load companies");
		return getSession().createQuery("from Company").getResultList();
	}
	@Override
	public void createEntity(Company company) {
		getSession().persist(company);

	}

	@Override
	public Company findById(int key) {
		Company company_result = getSession().load(Company.class, key, LockMode.OPTIMISTIC);
		if (company_result == null) {
			logger.error("Null object");
			return null;
		}
		else return company_result;	
	}

}
