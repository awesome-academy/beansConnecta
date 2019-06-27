package vn.sun.controller.client;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.context.i18n.LocaleContextHolder;

import vn.sun.bean.UserInfo;
import vn.sun.entities.User;
import vn.sun.helper.UserConvertHelper;

@Controller
public class RegisterController extends BaseController {

	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
	
	@GetMapping("/register")
	public String register(@ModelAttribute("userInfo") UserInfo userInfo) {
		return "/client/views/register";
	}

	@PostMapping(path = "/register")
	public String addUser(
		final Model model,
		@ModelAttribute("userInfo") @Valid UserInfo userInfo,
		BindingResult bindingResult,
		HttpSession httpSession,
		final RedirectAttributes redirectAttributes) {

		if(!validateRegisterForm(model, userInfo, bindingResult)) 
			return "/client/views/register"; 
	
		
		User user =  UserConvertHelper.convertUserInfoToUserForRegister(userInfo);
		userService.saveUserAfterRegister(user);
		// some mail services here later
		return "redirect:/";
	}

	private boolean validateRegisterForm(final Model model, UserInfo userInfo, 
			BindingResult bindingResult) {

		if(userService.isUserEmailExisted(userInfo.getEmail())) {
			model.addAttribute(
				"emailExistedError", 
				messageSource.getMessage("user.register.emailExisted", null, LocaleContextHolder.getLocale())
				);
			return false;
		}
		if(bindingResult.hasErrors()) return false;
	
		return true;
	}
}
