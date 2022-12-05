package com.co.andresfot.libreria.model.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.co.andresfot.libreria.model.entity.Libro;

public interface ILibroDao extends PagingAndSortingRepository<Libro, Long>{

}
