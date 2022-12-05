package com.co.andresfot.libreria.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.co.andresfot.libreria.model.dao.ILibroDao;
import com.co.andresfot.libreria.model.entity.Libro;

@Service
public class LibroServiceImpl implements ILibroService{
	
	@Autowired
	private ILibroDao libroDao;

	@Override
	public List<Libro> findAll() {
		return (List<Libro>) libroDao.findAll();
	}
	
	@Override
	public Page<Libro> findAll(Pageable pageable) {
		return libroDao.findAll(pageable);
	}

	@Override
	public void save(Libro libro) {
		libroDao.save(libro);
	}

	@Override
	public Libro findOne(Long id) {
		return libroDao.findById(id).orElse(null);
	}

	@Override
	public void delete(Long id) {
		libroDao.deleteById(id);
	}

}
