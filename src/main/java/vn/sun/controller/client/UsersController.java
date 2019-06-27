package vn.sun.controller.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.sun.services.client.UserServices;

@Controller
@RequestMapping("/users")
public class UsersController {

	@Autowired(required = true)
	private UserServices userService;

	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("users", userService.loadUsers());
		return "client/views/users";
	}
}
