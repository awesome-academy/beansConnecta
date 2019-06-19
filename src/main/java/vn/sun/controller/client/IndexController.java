package vn.sun.controller.client;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	@RequestMapping("/")
	public String hello() {
		return "client/views/index";
	}
	
	@RequestMapping("/login")
	public String login() {
		return "client/views/login";
	}
}
