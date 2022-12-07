package com.co.andresfot.libreria.controller;

import java.util.List;

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

import com.co.andresfot.libreria.model.entity.Autor;
import com.co.andresfot.libreria.model.entity.Libro;
import com.co.andresfot.libreria.model.service.IAutorService;
import com.co.andresfot.libreria.model.service.ILibroService;
import com.co.andresfot.libreria.util.paginator.PageRender;

@Controller
@RequestMapping("/libros")
@SessionAttributes("libro")
public class LibroController {

	@Autowired
	private ILibroService libroService;

	@Autowired
	private IAutorService autorService;

	@GetMapping("/lista-libros")
	public String listarLibros(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {

		Pageable pageable = PageRequest.of(page, 5);

		Page<Libro> libros = libroService.findAll(pageable);

		PageRender<Libro> pageRender = new PageRender<>("/lista-libros", libros);

		model.addAttribute("titulo", "Listado de Libros");
		model.addAttribute("libros", libros);
		model.addAttribute("page", pageRender);

		return "libros/listado-libros";
	}

	@GetMapping("/crear-libro")
	public String crearLibro(Model model) {

		List<Autor> autores = autorService.findAll();

		model.addAttribute("titulo", "Añadir nuevo Libro");
		Libro libro = new Libro();

		model.addAttribute("libro", libro);
		model.addAttribute("autores", autores);

		return "libros/nuevo-libro"; // html
	}

	@PostMapping("/crear-libro")
	public String crearLibro(@Valid Libro libro, BindingResult result, Model model) {

		List<Autor> autores = autorService.findAll();

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Añadir nuevo Libro");
			model.addAttribute("libro", libro);
			model.addAttribute("autores", autores);
			return "libros/nuevo-libro";
		}

		for (int i = 0; i < autores.size(); i++) {
			libro.setAutor(autores.get(i));
		}

		libroService.save(libro);

		return "redirect:/libros/lista-libros";
	}

	@GetMapping("/editar-libro/{id}")
	public String editarLibro(@PathVariable Long id, Model model) {

		Libro libro = null;
		List<Autor> autores = autorService.findAll();

		if (id > 0) {
			libro = libroService.findOne(id);

			if (libro == null) {
				return "redirect:/libros/lista-libros";
			}
		} else {
			return "redirect:/libros/lista-libros";
		}
		
		model.addAttribute("titulo", "Editar libro");
		model.addAttribute("libro", libro);
		model.addAttribute("autores", autores);

		return "libros/nuevo-libro";
	}
	
	@GetMapping("/eliminar-libro/{id}")
	public String eliminarLibro(@PathVariable Long id) {
		
		if(id > 0) {
			libroService.delete(id);
		}
		
		return "redirect:/lista-libros";
	}

}
