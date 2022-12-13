package com.co.andresfot.libreria.model.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.co.andresfot.libreria.model.entity.Prestamo;

public interface IPrestamoService {

	public List<Prestamo> findAll();
	
	public Page<Prestamo> findAllPaginable(Pageable pageable);

	public void save(Prestamo prestamo);

	public Prestamo findOne(Long id);
	
	public Prestamo findPrestamoByIdWithLibros(Long id);

	public void delete(Long id);

}
