package com.co.andresfot.libreria.model.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.co.andresfot.libreria.model.entity.Usuario;

public interface IUsuarioDao extends PagingAndSortingRepository<Usuario, Long>{

}
