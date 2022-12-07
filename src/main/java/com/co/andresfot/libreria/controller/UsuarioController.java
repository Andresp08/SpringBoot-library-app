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

import com.co.andresfot.libreria.model.entity.Usuario;
import com.co.andresfot.libreria.model.service.IPrestamoService;
import com.co.andresfot.libreria.model.service.IUsuarioService;
import com.co.andresfot.libreria.util.paginator.PageRender;

@Controller
@RequestMapping("/usuarios")
@SessionAttributes("usuario")
public class UsuarioController {
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired IPrestamoService prestamoService;

	@GetMapping("/lista-usuarios")
	public String listadoUsuarios(@RequestParam(name = "page", defaultValue = "0") int page, 
			Model model) {
		
		Pageable pageable = PageRequest.of(page, 5);
		
		Page<Usuario> usuarios = usuarioService.findAll(pageable);
		
		PageRender<Usuario> pageRender = new PageRender<>("/lista-usuarios", usuarios);
		
		model.addAttribute("titulo", "Listado de Usuarios");
		model.addAttribute("usuarios", usuarios);
		model.addAttribute("page", pageRender);
		
		return "usuarios/listado-usuarios";
	}
	
	@GetMapping("/crear-usuario")
	public String crearUsuario(Model model) {
		
		Usuario usuario = new Usuario();
		
		model.addAttribute("titulo", "Añadir nuevo usuario");
		model.addAttribute("usuario", usuario);
		
		return "usuarios/nuevo-usuario";
	}
	
	@PostMapping("/crear-usuario")
	public String guardarAutor(@Valid Usuario usuario, BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Añadir nuevo usuario");
			model.addAttribute("usuario", usuario);
			return "usuarios/nuevo-usuario"; //html
		}
		
		usuarioService.save(usuario);
		
		return "redirect:/usuarios/lista-usuarios";
	}
	
	@GetMapping("/editar-usuario/{id}")
	public String editarUsuario(@PathVariable(value = "id") Long id, Model model) {
		
		Usuario usuario = null;
		
		if(id > 0 ) {
			usuario = usuarioService.findOne(id);
			
			if(usuario == null) {
				return "redirect:/usuarios/lista-usuarios";
			}
		}else {
			return "redirect:/usuarios/lista-usuarios";
		}
		
		model.addAttribute("titulo", "Editar Usuario");
		model.addAttribute("usuario", usuario);
		
		return "usuarios/nuevo-usuario";
	}
	
	@GetMapping("/ver/{id}")
	public String verUsuario(@PathVariable(value = "id") Long id, Model model) {
		
		Usuario usuario = usuarioService.fetchByIdWithPrestamos(id);
		
		if(usuario == null) {
			return "redirect:/lista-usuarios";
		}
		
		model.addAttribute("usuario", usuario);
		//model.addAttribute("prestamo", prestamo);
		model.addAttribute("titulo", "Detalle del usuario: " + usuario.getNombre());
		
		return "usuarios/ver";
	}
	
	@GetMapping("/eliminar-usuario/{id}")
	public String eliminarUsuario(@PathVariable Long id) {
		
		if(id > 0) {
			usuarioService.delete(id);
		}
		
		return "redirect:/usuarios/lista-usuarios";
	}
	
}
