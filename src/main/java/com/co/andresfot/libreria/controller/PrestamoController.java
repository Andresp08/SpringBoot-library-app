package com.co.andresfot.libreria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.co.andresfot.libreria.model.entity.Prestamo;
import com.co.andresfot.libreria.model.service.IPrestamoService;

@Controller
@RequestMapping("/prestamo")
public class PrestamoController {

	@Autowired
	private IPrestamoService prestamoService;
	
	@GetMapping("/detalle/{id}")
	public String detallePrestamo(@PathVariable(value = "id") Long id, Model model) {
		
		Prestamo prestamo = prestamoService.findPrestamoByIdWithLibros(id);
		
		if(prestamo == null) {
			return "redirect:/lista-usuarios";
		}
		
		model.addAttribute("titulo", "Detalle del prestamo");
		model.addAttribute("prestamo", prestamo);
		
		return "prestamo/detalle";
	}
	
}
