package vn.sun.controller.client;

import javax.servlet.http.HttpServletRequest;
import vn.sun.helper.Constants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.sun.entities.Job;
import vn.sun.services.client.CompanyServices;
import vn.sun.services.client.JobServices;
import vn.sun.services.client.JobTypeServices;

@Controller
@RequestMapping("/jobs")
public class JobsController extends BaseController {

	@Autowired(required = true)
	private JobTypeServices jobTypeService;
	@Autowired(required = true)
	private JobServices jobService;
	@Autowired(required = true)
	private CompanyServices companyService;
	
	@RequestMapping("/")
	public String index(HttpServletRequest request, ModelMap modelMap) {
		PagedListHolder jobs = new PagedListHolder<Job>(jobService.loadJobs());
		int page = ServletRequestUtils.getIntParameter(request, "page", 0);
		jobs.setPage(page);
		jobs.setPageSize(Constants.JOB_PAGESIZE);
		modelMap.addAttribute("jobs", jobs);
		modelMap.addAttribute("topActiveCompanies", companyService.loadTopActive());
		return "client/views/jobs/index-jobs";
	}

	@RequestMapping("/types")
	public String jobTypesIndex(Model model) {
		model.addAttribute("jobTypes", jobTypeService.loadJobTypes());
		return "client/views/jobs/index-jobTypes";
	}
}
