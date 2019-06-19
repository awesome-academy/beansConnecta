package vn.sun.DAO.client;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

public abstract class AbstractBaseDAO<PK extends Serializable, T> extends HibernateDaoSupport {

	@Autowired
	SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public T findById(Serializable key) {
		return (T) getSession().get(getPersistentClass(), key);
	}

	public void persist(T entity) {
		getSession().persist(entity);
	}

	public void delete(T entity) {
		getSession().delete(entity);
	}

	public T saveOrUpdate(T entity) {
		getSession().saveOrUpdate(entity);
		return entity;
	}

	private Class<T> persistentClass;

	public Class<T> getPersistentClass() {
		return persistentClass;
	}

	public AbstractBaseDAO(Class<T> persistentClass) {
		this.persistentClass = persistentClass;
	}

	@SuppressWarnings("unchecked")
	public AbstractBaseDAO() {
		this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[1];
	}

}
