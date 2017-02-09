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

public class FiltrarLibrosPorCategoriaAccion extends Accion {

	@Override
	public String ejecutar(HttpServletRequest request, 
			HttpServletResponse response) {

		CategoriaDAO categoriaDAO = new CategoriaDAOJPAImpl();
		LibroDAO libroDAO = new LibroDAOJPAImpl();
		List<Libro> listaDeLibros = null;
		List<Categoria> listaDeCategorias = categoriaDAO.buscarTodos();
		if (request.getParameter("categoria") == null 
				|| request.getParameter("categoria").equals("seleccionar")) {
			listaDeLibros = libroDAO.buscarTodos();
		} else {
			Categoria categoria=categoriaDAO.buscarPorClave(Integer.parseInt(request.getParameter("categoria")));
			listaDeLibros = libroDAO.buscarPorCategoria(categoria);
		}
		request.setAttribute("listaDeLibros", listaDeLibros);
		request.setAttribute("listaDeCategorias", listaDeCategorias);
		return "MostrarLibros.jsp";
	}

}
