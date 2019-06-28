package vn.sun.controller.client;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UsersController extends BaseController{

	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("users", userService.loadUsers());
		return "client/views/users";
	}
}
