package com.co.andresfot.libreria.model.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.co.andresfot.libreria.model.entity.Prestamo;

public interface IPrestamoDao extends PagingAndSortingRepository<Prestamo, Long>{

	@Query("SELECT p FROM Prestamo p LEFT JOIN p.libro l WHERE p.id=?1")
	public Prestamo fetchByIdWithLibros(Long id);
	
}
