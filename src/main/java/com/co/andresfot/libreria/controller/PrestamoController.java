package com.co.andresfot.libreria.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.co.andresfot.libreria.model.entity.Libro;
import com.co.andresfot.libreria.model.entity.Prestamo;
import com.co.andresfot.libreria.model.entity.Usuario;
import com.co.andresfot.libreria.model.service.ILibroService;
import com.co.andresfot.libreria.model.service.IPrestamoService;
import com.co.andresfot.libreria.model.service.IUsuarioService;
import com.co.andresfot.libreria.util.paginator.PageRender;

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
	
	@SuppressWarnings("unused")
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@GetMapping("/lista-prestamos")
	public String listadoPrestamos(@RequestParam(name = "page", defaultValue = "0") int page,
			Model model) {
		
		
		Pageable pageable = PageRequest.of(page, 8);
		
		Page<Prestamo> prestamos = prestamoService.findAllPaginable(pageable);
		
		PageRender<Prestamo> pageRender = new PageRender<>("/prestamos/lista-prestamos", prestamos);
		
		model.addAttribute("titulo", "Listado de Prestamos");
		model.addAttribute("prestamos", prestamos);
		model.addAttribute("page", pageRender);
		
		
		return "prestamo/listado-prestamos";
	}
	
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
	
	@PostMapping("/crear-prestamo")
	public String guardarPrestamo(@RequestParam(name = "libro", required = false) Long libroId,
			@RequestParam(name = "usuario", required = false) Long usuarioId,
			@Valid Prestamo prestamo, BindingResult result, Model model, 
			SessionStatus status, RedirectAttributes flash) {
		
		List<Usuario> usuarios = usuarioService.findAll();
		List<Libro> libros = libroService.findAll();
		
		Libro libro = libroService.findOne(libroId);
		Usuario usuario = usuarioService.findOne(usuarioId);
		
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Crear nuevo prestamo");
			model.addAttribute("prestamo", prestamo);
			model.addAttribute("usuarios", usuarios);
			model.addAttribute("libros", libros);
	
			return "prestamo/nuevo-prestamo";
		}
		
		prestamo.setLibro(libro);
		prestamo.setUsuario(usuario);
		
		if(prestamo.getDevuelto()) {
			prestamo.setDevuelto(true);
		} else {
			prestamo.setDevuelto(false);
		}
		
		prestamoService.save(prestamo);
		status.setComplete();
		flash.addFlashAttribute("success", "Prestamo creado con exito!!");
		
		return "redirect:/prestamos/lista-prestamos";
	}
	
	@GetMapping("/editar-prestamo/{id}")
	public String editarPrestamo(@PathVariable Long id, Model model, RedirectAttributes flash) {

		Prestamo prestamo = null;
		List<Usuario> usuarios = usuarioService.findAll();
		List<Libro> libros = libroService.findAll();

		if (id > 0) {
			prestamo = prestamoService.findOne(id);

			if (prestamo == null) {
				flash.addFlashAttribute("error", "El prestamo no existe en la BBDD!!");
				return "redirect:/prestamos/lista-prestamos";
			}
		} else {
			return "redirect:/prestamos/lista-prestamos";
		}
		
		model.addAttribute("titulo", "Editar Prestamo");
		model.addAttribute("prestamo", prestamo);
		model.addAttribute("usuarios", usuarios);
		model.addAttribute("libros", libros);
		

		return "prestamo/nuevo-prestamo";
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
	
	@GetMapping("/eliminar-prestamo/{id}")
	public String eliminarPrestamo(@PathVariable Long id, RedirectAttributes flash) {
		
		if (id > 0) {
			prestamoService.delete(id);
			flash.addFlashAttribute("success", "Prestamo eliminado con exito");
		}
		
		return "redirect:/prestamos/lista-prestamos";
	}
	
}

