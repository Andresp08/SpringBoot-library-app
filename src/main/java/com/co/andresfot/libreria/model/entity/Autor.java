package com.co.andresfot.libreria.model.entity;

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

@Entity
@Table(name = "autor")
public class Autor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	private String nombre;
	
	@NotEmpty
	private String nacionalidad;
	
	@OneToMany(mappedBy = "libro_autor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name= "autor_id")
	private LibroAutor libroAutor;
	
}
