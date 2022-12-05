package com.co.andresfot.libreria.model.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.co.andresfot.libreria.model.entity.Libro;

public interface ILibroService {

	public List<Libro> findAll();
	
	public Page<Libro> findAll(Pageable pageable);

	public void save(Libro libro);

	public Libro findOne(Long id);

	public void delete(Long id);
	
}
