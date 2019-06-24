package vn.sun.controller.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.sun.services.client.JobTypesServices;

@Controller
@RequestMapping("/jobs")
public class JobsController {
	
	@Autowired(required = true)
	private JobTypesServices jobTypeService;
	
	@RequestMapping("/")
	public String index(Model model) {
		return "client/views/job-list";
	}
	
	@RequestMapping("/types")
	public String jobTypesIndex(Model model) {
		model.addAttribute("jobTypes", jobTypeService.loadJobTypes());
		return "client/views/job-types";
	}
}
