package com.arquitecturajava.aplicacion.controlador;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.arquitecturajava.aplicacion.controlador.acciones.Accion;

/**
 * Servlet implementation class ControladorLibros
 */
// @WebServlet("/ControladorLibros")
public class ControladorLibros extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());
		RequestDispatcher despachador = null;
		String url=request.getServletPath();
		Accion accion= Accion.getAccion(getNombreAccion(url));
		despachador = request.getRequestDispatcher(accion.ejecutar(request,
				response));
		despachador.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	private String getNombreAccion(String url) {
		
		
		return url.substring(1,url.length()-3);
		
	}

}
