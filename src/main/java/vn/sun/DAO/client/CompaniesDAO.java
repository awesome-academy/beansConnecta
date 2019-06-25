package vn.sun.DAO.client;

import java.util.List;

import vn.sun.entities.Companies;

public interface CompaniesDAO extends BaseDAO<Integer, Companies> {
	List<Companies> loadEntities();
}
