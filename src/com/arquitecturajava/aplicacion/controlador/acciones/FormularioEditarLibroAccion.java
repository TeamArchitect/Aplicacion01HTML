package com.arquitecturajava.aplicacion.controlador.acciones;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.arquitecturajava.aplicacion.bo.Categoria;
import com.arquitecturajava.aplicacion.bo.Libro;
import com.arquitecturajava.aplicacion.dao.CategoriaDAO;
import com.arquitecturajava.aplicacion.dao.LibroDAO;
import com.arquitecturajava.aplicacion.dao.jpa.CategoriaDAOJPAImpl;
import com.arquitecturajava.aplicacion.dao.jpa.LibroDAOJPAImpl;

public class FormularioEditarLibroAccion extends Accion{

	@Override
	public String ejecutar(HttpServletRequest request, 
			HttpServletResponse response) {
		CategoriaDAO categoriaDAO= new CategoriaDAOJPAImpl();
		LibroDAO libroDAO = new LibroDAOJPAImpl();
		String isbn = request.getParameter("isbn");
		List<Categoria> listaDeCategorias = categoriaDAO.buscarTodos();
		Libro libro = libroDAO
				.buscarPorClave(isbn);
		request.setAttribute("listaDeCategorias", listaDeCategorias);
		request.setAttribute("libro", libro);
		return "FormularioEditarLibro.jsp";
	}

}
