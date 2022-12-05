package com.co.andresfot.libreria.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.co.andresfot.libreria.model.entity.Prestamo;

public interface IPrestamoDao extends CrudRepository<Prestamo, Long>{

}
