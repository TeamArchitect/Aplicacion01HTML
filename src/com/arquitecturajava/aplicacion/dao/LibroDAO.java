package com.arquitecturajava.aplicacion.dao;

import java.util.List;

import com.arquitecturajava.aplicacion.bo.Categoria;
import com.arquitecturajava.aplicacion.bo.Libro;

public interface LibroDAO extends GenericDAO<Libro, String> {
	public List<Libro> buscarPorCategoria(Categoria categoria);
}
