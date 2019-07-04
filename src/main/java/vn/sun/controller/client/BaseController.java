package vn.sun.controller.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import vn.sun.services.client.CandidateServices;
import vn.sun.services.client.CompanyServices;
import vn.sun.services.client.JobServices;
import vn.sun.services.client.JobTypeServices;
import vn.sun.services.client.UserServices;

public class BaseController {

	@Autowired
	protected UserServices userService;
	@Autowired
	protected JobTypeServices jobTypeService;
	@Autowired
	protected CompanyServices companyService;
	@Autowired
	protected CandidateServices candidateService;
	@Autowired
	protected JobServices jobService;
	
	@Autowired
	protected MessageSource messageSource;
}
