package com.arquitecturajava.aplicacion.controlador.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.arquitecturajava.aplicacion.bo.Libro;
import com.arquitecturajava.aplicacion.dao.LibroDAO;
import com.arquitecturajava.aplicacion.dao.jpa.LibroDAOJPAImpl;

public class BorrarLibroAccion extends Accion {

	@Override
	public String ejecutar(HttpServletRequest request,
			HttpServletResponse response) {
		
		
		String isbn = request.getParameter("isbn");
		Libro libro = new Libro(isbn);
		LibroDAO libroDAO = new LibroDAOJPAImpl();

		libroDAO.borrar(libro);
		return "MostrarLibros.do";
	}
}
