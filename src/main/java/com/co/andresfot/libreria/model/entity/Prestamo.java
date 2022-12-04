package com.co.andresfot.libreria.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "prestamo")
public class Prestamo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Column(name = "fecha_prestamo")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaPrestamo;
	
	@NotNull
	@Column(name = "fecha_devolucion")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaDevolucion;
	
	@NotNull
	private boolean devuelto;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Usuario usuario;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Libro libro;
	
	@PrePersist
	public void Prepersist() {
		fechaPrestamo = new Date();
	}
}
