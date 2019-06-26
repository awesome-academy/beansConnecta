package vn.sun.controller.client;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.sun.entities.Company;
import vn.sun.services.client.CompaniesServices;

@Controller
@RequestMapping("/companies")
public class CompaniesController {
	
	@Autowired(required = true)
	private CompaniesServices companyService;
	
	@RequestMapping("/")
	public String index(HttpServletRequest request, Model modelMap) {
		PagedListHolder companies = new PagedListHolder<Company>(companyService.loadCompanies());
		int page = ServletRequestUtils.getIntParameter(request, "page", 0);
		companies.setPage(page);
		companies.setPageSize(2);
		modelMap.addAttribute("companies", companies);
		return "client/views/companies/index-companies";
	}

}
