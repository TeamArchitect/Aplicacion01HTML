package com.arquitecturajava.aplicacion.controlador.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.arquitecturajava.aplicacion.bo.Categoria;
import com.arquitecturajava.aplicacion.bo.Libro;
import com.arquitecturajava.aplicacion.dao.CategoriaDAO;
import com.arquitecturajava.aplicacion.dao.LibroDAO;
import com.arquitecturajava.aplicacion.dao.jpa.CategoriaDAOJPAImpl;
import com.arquitecturajava.aplicacion.dao.jpa.LibroDAOJPAImpl;

public class ModificarLibroAccion extends Accion{

	@Override
	public String ejecutar(HttpServletRequest request,
			HttpServletResponse response) {
		String isbn = request.getParameter("isbn");
		String titulo = request.getParameter("titulo");
		String categoria = request.getParameter("categoria");
		CategoriaDAO categoriaDAO= new CategoriaDAOJPAImpl();
		LibroDAO libroDAO= new LibroDAOJPAImpl();
		Categoria categoriaObjeto=categoriaDAO.buscarPorClave(Integer.parseInt(categoria));
		Libro libro = new Libro(isbn, titulo, categoriaObjeto);
		libroDAO.salvar(libro);
		return "MostrarLibros.do";
	}

}
