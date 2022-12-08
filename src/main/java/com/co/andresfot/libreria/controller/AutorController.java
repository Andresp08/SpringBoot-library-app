package com.co.andresfot.libreria.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.co.andresfot.libreria.model.entity.Autor;
import com.co.andresfot.libreria.model.service.IAutorService;
import com.co.andresfot.libreria.util.paginator.PageRender;

@Controller
@RequestMapping("/autores")
@SessionAttributes("autor")
public class AutorController {
	
	@Autowired
	private IAutorService autorService;

	@GetMapping("/lista-autores")
	public String listadoAutores (@RequestParam(name = "page", defaultValue = "0") int page, 
			Model model) {
		
		Pageable pageable = PageRequest.of(page, 5);
		
		Page<Autor> autores = autorService.findAll(pageable);
		
		PageRender<Autor> pageRender = new PageRender<>("/autores/lista-autores", autores);
		
		model.addAttribute("titulo", "Listado de Autores");
		model.addAttribute("autores", autores);
		model.addAttribute("page", pageRender);
		
		return "autores/listado-autores";
	}
	
	@GetMapping("/crear-autor")
	public String crearAutor(Model model) {
		
		Autor autor = new Autor();
		
		model.addAttribute("titulo", "Añadir nuevo autor");
		model.addAttribute("autor", autor);
		
		return "autores/nuevo-autor";
	}
	
	@PostMapping("/crear-autor")
	public String guardarAutor(@Valid Autor autor, BindingResult result, Model model, 
			SessionStatus status) {
		
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Añadir nuevo autor");
			model.addAttribute("autor", autor);
			return "autores/nuevo-autor"; //html
		}
		
		autorService.save(autor);
		status.setComplete();
		
		return "redirect:/autores/lista-autores";
	}
	
	@GetMapping("/editar-autor/{id}")
	public String editarAutor(@PathVariable Long id, Model model) {
		Autor autor = null;
		
		if(id > 0 ) {
			autor = autorService.findOne(id);
			
			if(autor == null) {
				return "redirect:/autores/lista-autores";
			}
		} else {
			System.out.println("El id no puede ser cero");
		}
		
		model.addAttribute("titulo", "Editar autor");
		model.addAttribute("autor", autor);
		
		return "autores/nuevo-autor";
	}
	
	@GetMapping("/eliminar-autor/{id}")
	public String eliminarAutor(@PathVariable Long id) {
		
		if(id > 0) {
			autorService.delete(id);
		}
		
		return "redirect:/autores/lista-autores";
	}
	
}
