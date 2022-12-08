package com.co.andresfot.libreria.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.co.andresfot.libreria.model.entity.Libro;
import com.co.andresfot.libreria.model.entity.Prestamo;
import com.co.andresfot.libreria.model.entity.Usuario;
import com.co.andresfot.libreria.model.service.ILibroService;
import com.co.andresfot.libreria.model.service.IPrestamoService;
import com.co.andresfot.libreria.model.service.IUsuarioService;

@Controller
@RequestMapping("/prestamos")
@SessionAttributes("prestamo")
public class PrestamoController {

	@Autowired
	private IPrestamoService prestamoService;
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private ILibroService libroService;
	
	@GetMapping("/crear-prestamo")
	public String crearPrestamo(Model model) {
		
		Prestamo prestamo = new Prestamo();
		List<Usuario> usuarios = usuarioService.findAll();
		List<Libro> libros = libroService.findAll();
		
		model.addAttribute("titulo", "Crear nuevo prestamo");
		model.addAttribute("prestamo", prestamo);
		model.addAttribute("usuarios", usuarios);
		model.addAttribute("libros", libros);
		
		return "prestamo/nuevo-prestamo";
	}
	
	@PostMapping("/guardar-prestamo")
	public String guardarPrestamo(@Valid Prestamo prestamo, BindingResult result, Model model, 
			SessionStatus status) {
		
		List<Usuario> usuarios = usuarioService.findAll();
		List<Libro> libros = libroService.findAll();
		
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Crear nuevo prestamo");
			model.addAttribute("prestamo", prestamo);
			model.addAttribute("usuarios", usuarios);
			model.addAttribute("libros", libros);
			
			System.out.println("Errores ");
			
			return "prestamo/nuevo-prestamo";
		}
		
		for (int i = 0; i < libros.size(); i++) {
			prestamo.setLibro(libros.get(i));
		}
		
		for (int i = 0; i < usuarios.size(); i++) {
			prestamo.setUsuario(usuarios.get(i));
		}
		
		prestamoService.save(prestamo);
		status.setComplete();
		
		return "redirect:/libros/lista-libros";
	}
	
	@GetMapping("/detalle/{id}")
	public String detallePrestamo(@PathVariable(value = "id") Long id, Model model) {
		
		Prestamo prestamo = prestamoService.findPrestamoByIdWithLibros(id);
		
		if(prestamo == null) {
			return "redirect:/lista-usuarios";
		}
		
		model.addAttribute("titulo", "Detalle del prestamo del Libro ");
		model.addAttribute("prestamo", prestamo);
		
		return "prestamo/detalle";
	}
	
}

