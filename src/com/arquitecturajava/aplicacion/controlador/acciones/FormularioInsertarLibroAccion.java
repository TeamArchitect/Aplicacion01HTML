package com.arquitecturajava.aplicacion.controlador.acciones;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.arquitecturajava.aplicacion.bo.Categoria;
import com.arquitecturajava.aplicacion.dao.CategoriaDAO;
import com.arquitecturajava.aplicacion.dao.jpa.CategoriaDAOJPAImpl;

public class FormularioInsertarLibroAccion extends Accion{

	@Override
	public String ejecutar(HttpServletRequest request,
			HttpServletResponse response) {
		
		
		List<Categoria> listaDeCategorias = null;
		CategoriaDAO categoriaDAO= new CategoriaDAOJPAImpl();
		listaDeCategorias = categoriaDAO.buscarTodos();
		request.setAttribute("listaDeCategorias", listaDeCategorias);
		return "FormularioInsertarLibro.jsp";
	}

}
