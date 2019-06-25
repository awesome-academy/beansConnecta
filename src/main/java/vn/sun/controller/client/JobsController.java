package vn.sun.controller.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.sun.services.client.JobTypeServices;

@Controller
@RequestMapping("/jobs")
public class JobsController extends BaseController {
	
	@Autowired(required = true)
	private JobTypeServices jobTypeService;
	
	@RequestMapping("/")
	public String index(Model model) {
		return "client/views/jobs/index-jobs";
	}
	
	@RequestMapping("/types")
	public String jobTypesIndex(Model model) {
		model.addAttribute("jobTypes", jobTypeService.loadJobTypes());
		return "client/views/jobs/index-jobTypes";
	}
}
