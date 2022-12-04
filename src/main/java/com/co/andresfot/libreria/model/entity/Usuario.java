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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	private String nombre;
	
	@NotEmpty
	@Email
	private String correo;
	
	@NotEmpty
	private String direccion;
	
	@OneToMany(mappedBy = "prestamo", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "usuario_id")
	private List<Prestamo> prestamos;
	
}
