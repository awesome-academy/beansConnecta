package vn.sun.DAO.client;

import java.util.List;

import vn.sun.entities.JobType;

public interface JobTypeDAO extends BaseDAO<Integer, JobType> {
	List<JobType> loadEntities();

}
