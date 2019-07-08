package vn.sun.controller.client;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import vn.sun.entities.Candidate;
import vn.sun.entities.User;
import vn.sun.helper.Constants;
import vn.sun.services.client.CandidateServices;

@Controller
@RequestMapping("/candidates")
public class CandidateController extends BaseController {

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

	@GetMapping(value = "/show")
	public String showProfile(Authentication authentication, HttpSession session, Model model) {
		User user = loadCurrentUser(authentication);
		if (user != null) {
			Candidate candidate = user.getCandidate();
			model.addAttribute("userInfo", user);
			model.addAttribute("candidateInfo", candidate);
			if(candidate.getCvfile() != null) model.addAttribute("hasCVFile", true);
			return "client/views/candidates/profile-candidate";
		}

		return "redirect:/";
	}

	@PostMapping(value = "/singleFileUpload")
	public String singleFileUpload(@RequestParam("file") MultipartFile file, final Model model,
			Authentication authentication) throws IOException {
		User user = loadCurrentUser(authentication);
		if (user != null) {
			Candidate candidate = user.getCandidate();
			model.addAttribute("userInfo", user);
			model.addAttribute("candidateInfo", candidate);
			if (!file.getOriginalFilename().isEmpty()) {
				candidate.setCvfile(file.getBytes());
				candidateService.saveOrUpdate(candidate);
				model.addAttribute("msg", "File uploaded successfully.");
			} else {
				model.addAttribute("msg", "Please select a valid file..");
			}
		}
		return "redirect:/candidates/show/";
	}

	@GetMapping(value = "/show/pdf")
	public ResponseEntity<byte[]> getPDF1(Authentication authentication) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/pdf"));
		String filename = "CV.pdf";

		headers.add("content-disposition", "inline;filename=" + filename);
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		byte[] pdfBytes = null;
		User user = loadCurrentUser(authentication);
		if (user != null) {
			Candidate candidate = user.getCandidate();
			pdfBytes = candidate.getCvfile();
		}
		ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(pdfBytes, headers, HttpStatus.OK);
		return response;
	}

	private User loadCurrentUser(Authentication authentication) {
		User user = null;
		if (!(authentication instanceof AnonymousAuthenticationToken) && authentication != null) {
			String authName = authentication.getName();
			user = userService.findByEmail(authName);
		}
		return user;
	}

}
