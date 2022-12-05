package com.co.andresfot.libreria.model.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.co.andresfot.libreria.model.entity.Autor;

public interface IAutorService {

	public List<Autor> findAll();

	public Page<Autor> findAll(Pageable pageable);

	public void save(Autor autor);

	public Autor findOne(Long id);

	public void delete(Long id);

}
