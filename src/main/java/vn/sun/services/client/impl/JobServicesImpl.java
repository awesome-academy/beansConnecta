package vn.sun.services.client.impl;

import java.io.IOException;
import java.io.Serializable;
import java.io.StringReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.util.CharArraySet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import vn.sun.DAO.client.JobDAO;
import vn.sun.entities.Job;
import vn.sun.services.client.JobServices;

public class JobServicesImpl implements JobServices {
	private static final Logger logger = Logger.getLogger(JobServicesImpl.class);
	static final Set<String> fobiddenWord = new HashSet<String>(
		       Arrays.asList("a", "an", "and", "are", "as", "at", "be", "but", "by",
		    		   "for", "if", "in", "into", "is", "it",
		    		   "no", "not", "of", "on", "or", "such",
		    		   "that", "the", "their", "then", "there", "these",
		    		   "they", "this", "to", "was", "will", "with"));
	Analyzer analyzer = new StandardAnalyzer(CharArraySet.EMPTY_SET);
	
	@Autowired
	private JobDAO JobDAO;

	public void setJobDAO(JobDAO jobDAO) {
		this.JobDAO = jobDAO;
	}

	@Override
	public List<Job> loadJobs(int firstResult, int maxResult) {
		try {
			return JobDAO.loadJobs(firstResult, maxResult);
		} catch (Exception e) {
			logger.error("has error by loadJob method");
			return null;
		}
	}

	@Override
	public Job findById(Serializable key) {
		try {
			Job result_job_type = JobDAO.findById(key);
			if (result_job_type != null) return result_job_type;
			logger.error("job type is null");
			return null;
		}
		catch (DataIntegrityViolationException e) {
			logger.error("Cant not find job type");
			return null;
		}
	}

	@Override
	public Job saveOrUpdate(Job job) {
		try {
			JobDAO.saveOrUpdate(job);
		} catch (DataIntegrityViolationException e) {
			logger.error("Cant save or update jobtypes");
			throw e;
		}
		return job;
	}

	@Override
	public void delete(Job job) {
		try {
			JobDAO.delete(job);
		} catch (DataIntegrityViolationException e) {
			logger.error("Cant delete this job types");
			throw e;
		}
	}

	@Override
	public Long countJobs(String keyword) {
		try {
			String searchKey = tokenizeString(analyzer, keyword);
			return JobDAO.countJobs(searchKey);
		} catch (Exception e) {
			logger.info("ERROR IN COUNTJOB" + e);
			return null;
		}
	}

	@Override
	public List<Job> search(String keyword, int firstResult, int maxResult) {
		try {
			String searchKey = tokenizeString(analyzer, keyword);
			return JobDAO.search(searchKey, firstResult, maxResult);
		} catch (Exception e) {
			logger.error("Error on full text search: " + e);
			return null;
		}
	}
	
    public static String tokenizeString(Analyzer analyzer, String string) throws IOException {
        String result = new String();
        TokenStream stream = analyzer.tokenStream(null, new StringReader(string));
        try
        {
            stream.reset();
            while (stream.incrementToken())
            {
            	String nextToken = stream.getAttribute(CharTermAttribute.class).toString();
                if(!fobiddenWord.contains(nextToken))
                	result += nextToken + " ";
                System.out.println("Insde tokenize" + result);
            }
        } catch (Exception e)
        {
        	logger.info("ERROR ON TOKENIZE STRING" + e);
            throw new RuntimeException(e);
        }
        finally {
        	stream.close();
        }
        return result;
    }
	
}
