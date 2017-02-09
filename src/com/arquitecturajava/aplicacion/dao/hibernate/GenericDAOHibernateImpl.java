package com.arquitecturajava.aplicacion.dao.hibernate;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.arquitecturajava.aplicacion.bo.Libro;
import com.arquitecturajava.aplicacion.dao.GenericDAO;

public class GenericDAOHibernateImpl<T, Id extends Serializable> implements GenericDAO<T, Id>{
	private Class<T> claseDePersistencia;
	
	@SuppressWarnings("unchecked")
	public GenericDAOHibernateImpl() {
		this.claseDePersistencia = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}

	@Override
	public T buscarPorClave(Id id) {
		// TODO Auto-generated method stub
		SessionFactory factoriaSession = HibernateHelper.getSessionFactory();
		Session session = factoriaSession.openSession();
		Object clase = session.load(claseDePersistencia.getClass(), id);
		 Hibernate.initialize(clase);
		return (T) clase;
	}

	@Override
	public List<T> buscarTodos() {
		SessionFactory factoriaSession = HibernateHelper.getSessionFactory();
		Session session = factoriaSession.openSession();
		List<T> listaDeObjetos = session.createQuery("from Libro libro right join fetch libro.categoria").list();
		session.close();
		return listaDeObjetos;
	}

	@Override
	public void borrar(T objeto) {
		SessionFactory factoriaSession = HibernateHelper.getSessionFactory();
		Session session = factoriaSession.openSession();
		session.beginTransaction();
		session.delete(objeto);
		session.getTransaction().commit();
	}
	
	@Override
	public void salvar(T objeto) {
		SessionFactory factoriaSession = HibernateHelper.getSessionFactory();
		Session session = factoriaSession.openSession();
		session.beginTransaction();
		session.saveOrUpdate(objeto);
		session.getTransaction().commit();	
	}


	@Override
	public void insertar(T objeto) {
		SessionFactory factoriaSession = HibernateHelper.getSessionFactory();
		Session session = factoriaSession.openSession();
		session.beginTransaction();
		session.save(objeto);
		session.getTransaction().commit();
		
	}





}
