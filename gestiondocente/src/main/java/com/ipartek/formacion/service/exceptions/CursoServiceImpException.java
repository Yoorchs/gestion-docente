package com.ipartek.formacion.service.exceptions;

public class CursoServiceImpException extends Exception{
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	public static final int COD_CURSO_NO_ENCONTRADO = 500;
	public static final String MSG_CURSO_NO_ENCONTRADO = "No se encuentra el curso solicitado ";
	
	private int codigo;
	private String mensaje;
	
	public CursoServiceImpException(String mensaje) {
		super();
		this.mensaje = mensaje;
	}
	
	public CursoServiceImpException(int codigo, String mensaje) {
		super();
		this.codigo = codigo;
		this.mensaje = mensaje;
	}

	public int getCodigo() {
		return codigo;
	}
	
}