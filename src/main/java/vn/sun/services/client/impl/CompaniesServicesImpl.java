package vn.sun.services.client.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import vn.sun.DAO.client.CompaniesDAO;
import vn.sun.entities.Company;
import vn.sun.services.client.CompaniesServices;

public class CompaniesServicesImpl implements CompaniesServices {
	private static final Logger logger = Logger.getLogger(CompaniesServicesImpl.class);

	@Autowired
	private CompaniesDAO CompaniesDAO;

	public CompaniesDAO getCompaniesDAO() {
		return CompaniesDAO;
	}

	public void setCompaniesDAO(CompaniesDAO companyDAO) {
		this.CompaniesDAO = companyDAO;
	}

	@Override
	public List<Company> loadCompanies() {
		try {
			return CompaniesDAO.loadEntities();
		} catch (Exception e) {
			logger.error("has error by loadCompanies method");
			return null;
		}
	}

}
