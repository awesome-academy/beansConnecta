package vn.sun.DAO.client;

import java.io.Serializable;

public interface BaseDAO<PK, T> {

	T saveOrUpdate(T entity);
	
	T findById(Serializable PK);
	
}
