package com.arquitecturajava.aplicacion.dao.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.arquitecturajava.aplicacion.bo.Categoria;
import com.arquitecturajava.aplicacion.bo.Libro;
import com.arquitecturajava.aplicacion.dao.LibroDAO;

public class LibroDAOHibernateImpl extends GenericDAOHibernateImpl<Libro, String> implements LibroDAO{

	public List<Libro> buscarPorCategoria(Categoria categoria)  {

		SessionFactory factoriaSession=HibernateHelper.getSessionFactory();
		Session session = factoriaSession.openSession();
		Query consulta=session.createQuery(" from Libro libro where libro.categoria=:categoria");
		consulta.setEntity("categoria", categoria);
		List<Libro> listaDeLibros = consulta.list();
		session.close();
		return listaDeLibros;
	
	}
}
