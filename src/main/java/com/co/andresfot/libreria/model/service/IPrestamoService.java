package com.co.andresfot.libreria.model.service;

import java.util.List;

import com.co.andresfot.libreria.model.entity.Prestamo;

public interface IPrestamoService {

	/* Prestamo */
	public List<Prestamo> findAll();

	public void save(Prestamo prestamo);

	public Prestamo findOne(Long id);

	public void delete(Long id);

}
