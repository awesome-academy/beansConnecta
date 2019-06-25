package vn.sun.controller.client;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RegisterController {

	@RequestMapping("/register")
	public String register(final Model model) {
		return "/client/views/register";
	}

}