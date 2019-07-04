package vn.sun.controller.client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import vn.sun.helper.Constants;
import vn.sun.helper.Page;
import vn.sun.helper.Paginator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import vn.sun.entities.Job;
import vn.sun.services.client.CompanyServices;
import vn.sun.services.client.JobServices;

@Controller
@RequestMapping("/jobs")
public class JobController extends BaseController {

	@Autowired(required = true)
	private JobServices jobService;
	@Autowired(required = true)
	private CompanyServices companyService;

	@RequestMapping("/")
	public String index(HttpServletRequest request, @RequestParam("keyword") Optional<String> keyword, ModelMap modelMap) {
		Integer page = ServletRequestUtils.getIntParameter(request, "page", 0);
		Integer firstResult = page * Constants.JOB_PAGESIZE;
		String searchKeyword = keyword.orElse("");
		
		modelMap.addAllAttributes(paginateSearch(searchKeyword, firstResult, Constants.JOB_PAGESIZE));
		modelMap.addAttribute("topActiveCompanies", companyService.loadTopActive());
		modelMap.addAttribute("jobTypes", jobTypeService.loadJobTypes());
		modelMap.addAttribute("page", page);
		modelMap.addAttribute("keyword", searchKeyword);
		return "client/views/jobs/index-jobs";
	}

	protected Map<String, Object> paginateSearch(String keyword, Integer firstResult, Integer maxResults) {
		long total = jobService.countJobs(keyword);
		Paginator myPaginator = new Paginator();
		Page page = myPaginator.getPage(firstResult, Constants.JOB_PAGESIZE, total);
		return buildListModel(jobService.search(keyword, page.getFirstResult(), page.getMaxResults()), page);
	}

	private Map<String, Object> buildListModel(List<Job> jobs, Page page) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("jobs", jobs);
		model.put("count", jobs.size());
		model.put("total", page.getTotal());
		model.put("firstResult", page.getFirstResult());
		model.put("maxResults", page.getMaxResults());
		model.put("nextResult", page.getNextResult());
		model.put("prevResult", page.getPrevResult());
		model.put("startResult", page.getStartResult());
		model.put("lastResult", page.getLastResult());
		model.put("lastPage", (int)((page.getTotal()-1) / page.getMaxResults()));
		return model;
	}

}
