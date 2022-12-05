package com.co.andresfot.libreria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.co.andresfot.libreria.model.entity.Prestamo;
import com.co.andresfot.libreria.model.entity.Usuario;
import com.co.andresfot.libreria.model.service.IPrestamoService;
import com.co.andresfot.libreria.model.service.IUsuarioService;
import com.co.andresfot.libreria.util.paginator.PageRender;

@Controller
@RequestMapping("/usuarios")
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
	
	@GetMapping("/ver/{id}")
	public String verUsuario(@PathVariable(value = "id") Long id, Model model) {
		
		Usuario usuario = usuarioService.fetchByIdWithPrestamos(id);
		
		//Prestamo prestamo = prestamoService.findPrestamoByIdWithLibros(id);
		
		if(usuario == null) {
			return "redirect:/lista-usuarios";
		}
		
		model.addAttribute("usuario", usuario);
		//model.addAttribute("prestamo", prestamo);
		model.addAttribute("titulo", "Detalle del usuario: " + usuario.getNombre());
		
		return "usuarios/ver";
	}
	
}
