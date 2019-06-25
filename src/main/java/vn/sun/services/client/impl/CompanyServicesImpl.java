package vn.sun.services.client.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import vn.sun.DAO.client.CompanyDAO;
import vn.sun.entities.Company;
import vn.sun.entities.Job;
import vn.sun.services.client.CompanyServices;

public class CompanyServicesImpl implements CompanyServices {
	private static final Logger logger = Logger.getLogger(CompanyServicesImpl.class);

	@Autowired
	private CompanyDAO CompanyDAO;

	public void setCompanyDAO(CompanyDAO companyDAO) {
		this.CompanyDAO = companyDAO;
	}

	@Override
	public List<Company> loadCompanies() {
		try {
			return CompanyDAO.loadEntities();
		} catch (Exception e) {
			logger.error("has error by loadCompanies method");
			return null;
		}
	}

	@Override
	public Company findById(Serializable key) {
		try {
			Company result_company = CompanyDAO.findById(key);
			if (result_company != null) return result_company;
			logger.error("company result is null");
			return null;

		} catch (DataIntegrityViolationException e) {
			logger.error("Cant not find this Company");
			return null;
		}

	}

	@Override
	public Company saveOrUpdate(Company company) {
		try {
			CompanyDAO.saveOrUpdate(company);
		} catch (DataIntegrityViolationException e) {
			logger.error("Cant save or update");
			throw e;
		}
		return company;
	}

	@Override
	public void delete(Company company) {
		try {
			CompanyDAO.delete(company);
		} catch (DataIntegrityViolationException e) {
			logger.error("Cant not delete");
			throw e;
		}
	}

	@Override
	public List<Job> loadCompanyJobs(Serializable key) {
		try {
			return CompanyDAO.loadCompanyJobs(key);
		} catch (Exception e) {
			logger.error("has error by loadCompanyJobs method");
			return null;
		}
	}
	
}
