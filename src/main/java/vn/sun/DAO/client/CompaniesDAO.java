package vn.sun.DAO.client;

import java.util.List;

import vn.sun.entities.Company;

public interface CompaniesDAO extends BaseDAO<Integer, Company> {
	List<Company> loadEntities();
}
