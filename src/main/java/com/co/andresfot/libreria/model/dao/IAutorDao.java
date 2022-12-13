package com.co.andresfot.libreria.model.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.co.andresfot.libreria.model.entity.Autor;

public interface IAutorDao extends PagingAndSortingRepository<Autor, Long>{	
	
}
