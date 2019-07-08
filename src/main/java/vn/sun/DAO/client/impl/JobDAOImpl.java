package vn.sun.DAO.client.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.search.Query;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Repository;

import vn.sun.DAO.client.AbstractBaseDAO;
import vn.sun.DAO.client.JobDAO;
import vn.sun.entities.Job;

@Repository
public class JobDAOImpl extends AbstractBaseDAO<Serializable, Job> implements JobDAO {



	@SuppressWarnings("unchecked")
	@Override
	public List<Job> loadEntities() {
		logger.info("load jobs");
		return getSession().createQuery("from Job").getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Job> loadJobs(int firstResult, int maxResult) {
		return getSession().createCriteria(Job.class).setFirstResult(firstResult).setMaxResults(maxResult).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Long countJobs(String keyword) {
		if (StringUtils.isBlank(keyword) || keyword.length()==0) {
			return (Long) getSession().createQuery("select count(*) from Job").iterate().next();
		}
		FullTextSession fullTextSession = Search.getFullTextSession(getSession());
		return (long) fullTextSession.createFullTextQuery(
				luceneQuery(fullTextSession, keyword), Job.class)
				.getResultSize();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Job> search(String keyword, int firstResult, int maxResult) {
		FullTextSession fullTextSession = Search.getFullTextSession(getSession());
		
		try {
			fullTextSession.createIndexer().startAndWait();
		} catch (InterruptedException e) {
			logger.error("Create lucene index failed");
		}

		if (StringUtils.isBlank(keyword) || keyword.length()==0) {
			return loadJobs(firstResult, maxResult);
		}
		
		org.hibernate.Query hibQuery = fullTextSession
				.createFullTextQuery(luceneQuery(fullTextSession, keyword), Job.class)
				.setFirstResult(firstResult)
				.setMaxResults(maxResult);
		
		List<Job> results = hibQuery.getResultList();
		return results;
	}

	public Query luceneQuery(FullTextSession fullTextSession, String keyword) {
		QueryBuilder qb = fullTextSession.getSearchFactory()
				.buildQueryBuilder().forEntity(Job.class)
				.get();
		return qb.keyword()
				.fuzzy()
				.withEditDistanceUpTo(2).withPrefixLength(0)
				.onFields("title", "description", "requirement")
				.matching(keyword).createQuery();
	}

}
