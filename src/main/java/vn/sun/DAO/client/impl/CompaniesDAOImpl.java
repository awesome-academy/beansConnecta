package vn.sun.DAO.client.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import vn.sun.DAO.client.AbstractBaseDAO;
import vn.sun.DAO.client.CompaniesDAO;
import vn.sun.entities.Company;

@Repository
public class CompaniesDAOImpl extends AbstractBaseDAO<Serializable, Company> implements CompaniesDAO {
	private static final Logger logger = Logger.getLogger(CompaniesDAOImpl.class);
	@SuppressWarnings("unchecked")
	@Override
	public List<Company> loadEntities() {
		logger.info("load companies");
		return getSession().createQuery("from Company").getResultList();
	}

}
