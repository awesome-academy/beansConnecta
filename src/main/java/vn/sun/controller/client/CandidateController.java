package vn.sun.controller.client;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.sun.entities.Candidate;
import vn.sun.helper.Constants;
import vn.sun.services.client.CandidateServices;

@Controller
@RequestMapping("/candidates")
public class CandidateController {

	@Autowired(required = true)
	private CandidateServices candidateService;
	
	@RequestMapping("/")
	public String index(HttpServletRequest request, Model model) {
		PagedListHolder<Candidate> candidates = new PagedListHolder<Candidate>(candidateService.loadCandidates());
		int page = ServletRequestUtils.getIntParameter(request, "page", 0);
		candidates.setPage(page);
		candidates.setPageSize(Constants.CANDIDATE_PAGESIZE);
		model.addAttribute("candidates", candidates);
		return "client/views/candidates/index-candidates";
	}
}
