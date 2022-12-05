package com.co.andresfot.libreria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.co.andresfot.libreria.model.entity.Libro;
import com.co.andresfot.libreria.model.service.ILibroService;
import com.co.andresfot.libreria.util.paginator.PageRender;

@Controller
@RequestMapping("/libros")
public class LibroController {
	
	@Autowired
	private ILibroService libroService;

	@GetMapping("/lista-libros")
	public String listarLibros(@RequestParam(name = "page", defaultValue = "0") int page, 
			Model model) {
		
		Pageable pageable = PageRequest.of(page, 5);
		
		Page<Libro> libros = libroService.findAll(pageable);
		
		PageRender<Libro> pageRender = new PageRender<>("/lista-libros", libros);
		
		model.addAttribute("titulo", "Listado de Libros");
		model.addAttribute("libros", libros);
		model.addAttribute("page", pageRender);
		
		return "libros/listado-libros";
	}
	
	
}
