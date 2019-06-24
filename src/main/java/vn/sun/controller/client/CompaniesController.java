package vn.sun.controller.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.sun.services.client.CompaniesServices;

@Controller
@RequestMapping("/companies")
public class CompaniesController {
	
	@Autowired(required = true)
	private CompaniesServices companyService;
	
	@RequestMapping("/")
	public String index(Model model) {
		model.addAttribute("companies", companyService.loadCompanies());
		return "client/views/companies/index-companies";
	}

}
