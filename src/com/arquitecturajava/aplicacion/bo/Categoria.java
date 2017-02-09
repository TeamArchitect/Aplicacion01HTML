package com.arquitecturajava.aplicacion.bo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Categorias")
public class Categoria {
	@Id
	private int id;
	private String descripcion;


	public Categoria() {
		super();
	}

	public Categoria(int id) {
		super();
		this.id = id;
	}

	public Categoria(int id, String descripcion) {
		super();
		this.id = id;
		this.descripcion = descripcion;
	}

	@Override
	public int hashCode() {
		return id;

	}

	@Override
	public boolean equals(Object o) {
		int categoriaId = ((Categoria) o).getId();
		return id == categoriaId;

	}
	@OneToMany(targetEntity = Libro.class)
	@JoinColumn(name = "categoria")
	private List<Libro> listaDeLibros;
	
	public List<Libro> getListaDeLibros() {
		return listaDeLibros;
	}

	public void setListaDeLibros(List<Libro> listaDeLibros) {
		this.listaDeLibros = listaDeLibros;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}



}
