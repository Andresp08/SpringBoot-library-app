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

import com.co.andresfot.libreria.model.entity.Autor;
import com.co.andresfot.libreria.model.service.IAutorService;
import com.co.andresfot.libreria.util.paginator.PageRender;

@Controller
@RequestMapping("/autores")
public class AutorController {
	
	@Autowired
	private IAutorService autorService;

	@GetMapping("/lista-autores")
	public String listadoAutores (@RequestParam(name = "page", defaultValue = "0") int page, 
			Model model) {
		
		Pageable pageable = PageRequest.of(page, 5);
		
		Page<Autor> autores = autorService.findAll(pageable);
		
		PageRender<Autor> pageRender = new PageRender<>("/lista-autores", autores);
		
		model.addAttribute("titulo", "Listado de Autores");
		model.addAttribute("autores", autores);
		model.addAttribute("page", pageRender);
		
		return "autores/listado-autores";
	}
	
}
