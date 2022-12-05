package com.co.andresfot.libreria.model.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.co.andresfot.libreria.model.entity.Usuario;

public interface IUsuarioService {

	public List<Usuario> findAll();
	
	public Page<Usuario> findAll(Pageable pageable);

	public void save(Usuario usuario);

	public Usuario findOne(Long id);
	
	public Usuario fetchByIdWithPrestamos(Long id);

	public void delete(Long id);
	
}
