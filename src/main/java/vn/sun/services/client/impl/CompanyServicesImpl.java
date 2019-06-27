package vn.sun.services.client.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import vn.sun.DAO.client.CompanyDAO;
import vn.sun.entities.Company;
import vn.sun.services.client.CompanyServices;

public class CompanyServicesImpl implements CompanyServices {
	private static final Logger logger = Logger.getLogger(CompanyServicesImpl.class);

	@Autowired
	private CompanyDAO CompanyDAO;

	public void setCompaniesDAO(CompanyDAO companyDAO) {
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
	public void createCompany() {
		Company company = new Company();
		try {
			CompanyDAO.createEntity(company);
		} catch (DataIntegrityViolationException e) {
			logger.error("Cant save");
		}
	}

}
