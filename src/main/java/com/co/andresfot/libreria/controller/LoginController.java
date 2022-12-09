package com.co.andresfot.libreria.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

	@GetMapping("/login")
	public String login(@RequestParam(value = "error", required = false) String error,
			Model model, Principal principal) {
		
		if(principal!=null) {
			return "redirect:/";
		}
		
		if(error != null) {
			model.addAttribute("error",
					"Error en el login: Nombre de usuario o contraseña incorrecta, "
					+ "por favor vuelva a intentarlo!");
		}
		
		
		
		return "login";
	}
	
}
