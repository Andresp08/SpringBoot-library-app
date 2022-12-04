package com.co.andresfot.libreria.model.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "libro")
public class Libro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	private String titulo;
	
	@NotEmpty
	private String editorial;
	
	@NotEmpty
	private String ISBN;
	
	@NotNull
	private boolean disponible_fisico;
	
	@OneToMany(mappedBy = "libro_autor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name= "libro_id")
	private LibroAutor libroAutor;
	
	@OneToMany(mappedBy = "prestamo", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "libro_id")
	private List<Prestamo> prestamos;
	

}
