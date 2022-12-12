

package com.blogapp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.blogapp.dto.RegisterDto;
import com.blogapp.entity.User;
import com.blogapp.service.UserService;

@Controller
public class AuthController {
	
	@Autowired
	private UserService userService;

	
	
	//Handler method for login at client page navbar
	@GetMapping("/login")
	public String loginPage() {
		return "login_form";
	}
	
	//Handler method for register link
	@GetMapping("/register")
	public String showRegisterForm(Model model) {
		
		RegisterDto user = new RegisterDto();
		model.addAttribute("user", user);
		return "register_form";
	}
	
	//Handler method for Save registration
	@PostMapping("/register/save")
	public String saveRegistration(@Valid @ModelAttribute("user") RegisterDto user,
								    BindingResult result ,Model model) {
		
		User emailUnique = userService.emailUnique(user.getEmail());
		
		if(emailUnique != null && emailUnique.getEmail() != null && !emailUnique.getEmail().isEmpty()) {
			result.rejectValue("email", null, "Email Id Already taken...");
		}
		
		if(result.hasErrors()) {
			model.addAttribute("user", user);
			return "register_form";
		}
		userService.saveUser(user);
		return "redirect:/register?success";
	}
}