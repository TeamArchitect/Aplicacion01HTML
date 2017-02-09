package com.arquitecturajava.main;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.arquitecturajava.aplicacion.HibernateHelper;
import com.arquitecturajava.aplicacion.bo.Categoria;
import com.arquitecturajava.aplicacion.bo.Libro;

public class Principal {

	public static void main(String[] args) {
		Principal.opcionSiete();
	}

	public static void opcionOne() {
		Session session = null;
		Transaction transaccion = null;
		try {
			// Create the SessionFactory from hibernate.cfg.xml
			SessionFactory factoria = new Configuration().configure().buildSessionFactory();
			session = factoria.openSession();
			transaccion = session.beginTransaction();
			// Libro l = new Libro("4", "Cocina", "Gastronomia");
			// session.saveOrUpdate(l);
			transaccion.commit();
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			transaccion.rollback();
		} catch (Exception e) {
			System.out.println("Soy un error: " + e);
		}
	}

	public static void opcionTwo() {
		Session session = null;
		try {
			SessionFactory factoria = new Configuration().configure().buildSessionFactory();
			session = factoria.openSession();
			Query consulta = session.createQuery("from Libro libro");
			ArrayList<Libro> lista = (ArrayList) consulta.list();
			for (Libro l : lista) {
				System.out.println(l.getIsbn());
				System.out.println(l.getTitulo());
				System.out.println(l.getCategoria());
			}
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
		} finally {
			session.close();
		}
	}

	public static void opcionThree() {
		Session session = null;
		try {
			SessionFactory factoria = new Configuration().configure().buildSessionFactory();
			session = factoria.openSession();
			Libro libro = (Libro) session.get(Libro.class, "1");
			System.out.println(libro.getTitulo());
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
		} finally {
			session.close();
		}
	}

	public static void opcionFour() {

		Session session = null;
		try {
			SessionFactory factoria = new Configuration().configure().buildSessionFactory();
			session = factoria.openSession();
			Libro libro = (Libro) session.get(Libro.class, "1");
			session.delete(libro);
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
		} finally {
			session.close();
		}
	}

	public static void opcionCinco() {
		Session session = null;
		try {
			SessionFactory factoria = new Configuration().configure().buildSessionFactory();
			session = factoria.openSession();
			Query consulta = session.createQuery(" from Libro libro where libro.categoria=:categoria");
			consulta.setString("categoria", "programacion");
			List<Libro> lista = consulta.list();
			for (Libro l : lista) {
				System.out.println(l.getIsbn());
				System.out.println(l.getTitulo());
				System.out.println(l.getCategoria());
			}
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
		} finally {
			session.close();
		}
	}

	public static void opcionSeis() {
		Session session = null;
		try {
			SessionFactory factoriaSession = HibernateHelper.getSessionFactory();
			session = factoriaSession.openSession();
			List<Libro> listaDeLibros = session.createQuery("from Libro libro").list();
			for (Libro l : listaDeLibros) {
				System.out.println(l.getTitulo());
				// accedemos a la categoria a traves de la relacion.
				System.out.println(l.getCategoria().getDescripcion());
			}
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
		} finally {
			session.close();
		}
	}

	public static void opcionSiete() {
		Session session = null;
		try {
			SessionFactory factoriaSession = HibernateHelper.getSessionFactory();
			session = factoriaSession.openSession();
			List<Libro> listaDeLibros = session.createQuery("from Libro libro right join fetch libro.categoria").list();
			for (Libro l : listaDeLibros) {
				System.out.println(l.getTitulo());
				// accedemos a la categoria a traves de la relacion.
				System.out.println(l.getCategoria().getDescripcion());
			}
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
		} finally {
			session.close();
		}
	}

	public static void opcionOcho() {
		Session session = null;
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("arquitecturaJava");
			EntityManager em = emf.createEntityManager();
			TypedQuery<Libro> consulta = em.createQuery("Select l from Libro l", Libro.class);
			List<Libro> lista = consulta.getResultList();
			for (Libro l : lista) {
				System.out.println(l.getTitulo());
			}
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
		} finally {
			session.close();
		}
	}

	public static void opcionNueve() {
		EntityTransaction tx = null;
		EntityManager manager = null;
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("arquitecturaJava");
			manager = emf.createEntityManager();
			Categoria cat = new Categoria(1);
			Libro libro = new Libro("2", "java", cat);
			
			tx = manager.getTransaction();
			tx.begin();
			manager.merge(libro);
			tx.commit();
			
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
		} finally {
			manager.close();
		}
	}
}
