package com.arquitecturajava.aplicacion.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import com.arquitecturajava.aplicacion.bo.Categoria;
import com.arquitecturajava.aplicacion.bo.Libro;
import com.arquitecturajava.aplicacion.dao.LibroDAO;

public class LibroDAOJPAImpl extends GenericDAOJPAImpl<Libro, String> implements LibroDAO{

	public List<Libro> buscarPorCategoria(Categoria categoria) {
		EntityManagerFactory factoriaSession = JPAHelper.getJPAFactory();
		EntityManager manager = factoriaSession.createEntityManager();
		TypedQuery<Libro> consulta = manager.createQuery(
				"Select l from Libro l where l.categoria=?1", Libro.class);
		consulta.setParameter(1, categoria);
		List<Libro> listaDeLibros = null;
		try {
			listaDeLibros = consulta.getResultList();
		} finally {
			manager.close();
		}
		return listaDeLibros;
	}
}
