package vn.sun.controller.client;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.sun.entities.Company;
import vn.sun.entities.Job;
import vn.sun.services.client.CompanyServices;

@Controller
@RequestMapping("/companies")
public class CompanyController {
	
	@Autowired(required = true)
	private CompanyServices companyService;
	
	@RequestMapping("/")
	public String index(HttpServletRequest request, Model modelMap) {
		PagedListHolder companies = new PagedListHolder<Company>(companyService.loadCompanies());
		int page = ServletRequestUtils.getIntParameter(request, "page", 0);
		companies.setPage(page);
		companies.setPageSize(2);
		modelMap.addAttribute("companies", companies);
		return "client/views/companies/index-companies";
	}
	
	@RequestMapping("/{id}/jobs")
	public String getCompanyJobs(@PathVariable(value="id") final Integer id, ModelMap modelMap) {
		List<Job> jobs = companyService.loadCompanyJobs(id);
		modelMap.addAttribute("jobs", jobs);
		modelMap.addAttribute("company", companyService.findById(id));
		return "client/views/companies/company-jobs";
	}

}
