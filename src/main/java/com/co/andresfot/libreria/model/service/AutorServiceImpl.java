package com.co.andresfot.libreria.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.co.andresfot.libreria.model.dao.IAutorDao;
import com.co.andresfot.libreria.model.entity.Autor;

@Service
public class AutorServiceImpl implements IAutorService {

	@Autowired
	private IAutorDao autorDao;

	@Override
	public List<Autor> findAll() {
		return (List<Autor>) autorDao.findAll();
	}

	@Override
	public Page<Autor> findAll(Pageable pageable) {
		return autorDao.findAll(pageable);
	}

	@Override
	public void save(Autor autor) {
		autorDao.save(autor);
	}

	@Override
	public Autor findOne(Long id) {
		return autorDao.findById(id).orElse(null);
	}

	@Override
	public void delete(Long id) {
		autorDao.deleteById(id);
	}
}
