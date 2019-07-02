package vn.sun.controller.client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import vn.sun.entities.Job;
import vn.sun.services.client.CompanyServices;
import vn.sun.services.client.JobServices;

@Controller
@RequestMapping("/jobs")
public class JobsController extends BaseController {

	@Autowired(required = true)
	private JobServices jobService;
	@Autowired(required = true)
	private CompanyServices companyService;

	@RequestMapping("/")
	public String index(HttpServletRequest request, ModelMap modelMap) {
		Integer page = ServletRequestUtils.getIntParameter(request, "page", 0);
		Integer start = page * Constants.JOB_PAGESIZE;

		modelMap.addAllAttributes(paginateJob(start,Constants.JOB_PAGESIZE));
		modelMap.addAttribute("topActiveCompanies", companyService.loadTopActive());
		modelMap.addAttribute("jobTypes", jobTypeService.loadJobTypes());
		modelMap.addAttribute("page",page);
		
		return "client/views/jobs/index-jobs";
	}

	@RequestMapping("/types")
	public String jobTypesIndex(Model model) {
		model.addAttribute("jobTypes", jobTypeService.loadJobTypes());
		return "client/views/jobs/index-jobTypes";
	}
	
    protected Map<String, Object> paginateJob(Integer firstResult, Integer maxResults) {
        long total = jobService.countAll();
        Paginator myPaginator = new Paginator();
        Page page = myPaginator.getPage(firstResult, 10, total);
        return buildListModel(
                jobService.loadJobs(page.getFirstResult(), page.getMaxResults()),
                page);
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
        model.put("lastPage",(int)page.getTotal() / page.getMaxResults());
        return model;
    }

}
