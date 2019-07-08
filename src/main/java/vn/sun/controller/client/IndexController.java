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
	
	@RequestMapping("/about")
	public String about() {
		return "client/views/about";
	}
}