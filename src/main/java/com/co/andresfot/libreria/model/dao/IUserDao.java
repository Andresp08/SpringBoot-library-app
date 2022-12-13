package com.co.andresfot.libreria.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.co.andresfot.libreria.model.entity.UserLogin;

public interface IUserDao extends CrudRepository<UserLogin, Long>{
	public UserLogin findByUsername(String username);
}
