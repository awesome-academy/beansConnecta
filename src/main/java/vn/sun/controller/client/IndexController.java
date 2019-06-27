package vn.sun.controller.client;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController extends BaseController {
	@RequestMapping("/")
	public String hello(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(!(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated()) {
			model.addAttribute("currentUser", auth.getName());
		}
		return "client/views/index";
	}

	@RequestMapping("/login")
	public String login(
			@RequestParam(value = "error", required = false) final String error,
			final Model model) {
		
		return "client/views/login";
	}

	@RequestMapping("/logout")
	public String logout(final Model model) {
		return "login";
	}
}