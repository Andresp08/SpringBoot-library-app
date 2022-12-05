package com.co.andresfot.libreria.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.andresfot.libreria.model.dao.IPrestamoDao;
import com.co.andresfot.libreria.model.entity.Prestamo;

@Service
public class PrestamoServiceImpl implements IPrestamoService {

	@Autowired
	private IPrestamoDao prestamoDao;

	@Override
	public List<Prestamo> findAll() {
		return (List<Prestamo>) prestamoDao.findAll();
	}

	@Override
	public void save(Prestamo prestamo) {
		prestamoDao.save(prestamo);
	}

	@Override
	public Prestamo findOne(Long id) {
		return prestamoDao.findById(id).orElse(null);
	}

	@Override
	public void delete(Long id) {
		prestamoDao.deleteById(id);
	}

}
