package com.arquitecturajava.aplicacion.controlador.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class Accion {
	public abstract String ejecutar(HttpServletRequest request, 
			HttpServletResponse response);

	public static Accion getAccion(String tipo) {
		Accion accion = null;
		try {
			accion = (Accion) Class.forName(getPackage()+"."+tipo+"Accion").newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return accion;
	}
	
	private static  String getPackage() {
		
		
		return Accion.class.getPackage().getName();
	}
}
