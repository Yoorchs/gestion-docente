package com.ipartek.formacion.dbms.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Curso {

	/**
	 * 
	 */
	public static final int CODIGO_NULO = -1;
	private int codigo;
	private String nombre;
	private int duracion;
	private Date fInicio;
	private Date fFinalizacion;
	private List<Alumno> alumnos;
	private Profesor profesor;
	

	public Curso() {
		super();
		this.codigo = CODIGO_NULO;
		this.nombre = "";
		this.duracion = 0;
		this.fInicio = new Date();
		this.fFinalizacion = new Date();
		this.alumnos = new ArrayList<Alumno>();
		this.profesor = null;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public Date getfFinalizacion() {
		return fFinalizacion;
	}

	public void setfFinalizacion(Date fFinalizacion) {
		this.fFinalizacion = fFinalizacion;
	}

	public List<Alumno> getAlumnos() {
		return alumnos;
	}

	public void setAlumnos(List<Alumno> alumnos) {
		this.alumnos = alumnos;
	}

	public Profesor getProfesor() {
		return profesor;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Date getfInicio() {
		return fInicio;
	}

	public void setfInicio(Date fInicio) {
		this.fInicio = fInicio;
	}

	@Override
	public String toString() {
		return "Curso [Codigo del curso=" + codigo + ", Nombre del curso=" + nombre + ", Duracion=" + duracion + ", Fecha de Inicio=" + fInicio
				+ ", Fecha de finalizacion=" + fFinalizacion + ", Alumnos matriculados=" + alumnos + ", Profesor=" + profesor + "]";
	}
	
}
