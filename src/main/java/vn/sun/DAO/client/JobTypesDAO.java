package vn.sun.DAO.client;

import java.util.List;

import vn.sun.entities.JobTypes;

public interface JobTypesDAO extends BaseDAO<Integer, JobTypes> {
	List<JobTypes> loadEntities();

}
