package com.ipartek.formacion.service;

import java.util.ArrayList;
import java.util.List;
//import com.ipartek.formacion.dbms.pojo.Alumno;
import com.ipartek.formacion.dbms.pojo.Curso;
//import com.ipartek.formacion.dbms.pojo.Profesor;
import com.ipartek.formacion.service.exceptions.CursoServiceImpException;

public class CursoserviceImp implements CursoService {

	private static int i = 0;
	private List<Curso> cursos;
	// private ArrayList<Alumno> alumnos;
	// private Profesor profesor;
	
	public CursoserviceImp() {
		super();
		cursos = new ArrayList<Curso>();
		// alumnos = new ArrayList<Alumno>();
		// profesor = new Profesor();
		init();
	}

	private void init() {
		Curso curso = new Curso();
		curso.setDuracion(12);
		curso.setNombre("POO");
		create(curso);
	}

	@Override
	public Curso create(Curso curso) {
		curso.setCodigo(i);
		i++;
		cursos.add(curso);
		return curso;
	}

	@Override
	public Curso getById(int codigo) {
		Curso curso = null;
		int posicion = -1;
		try {
			posicion = buscarCurso(codigo);
			curso = cursos.get(posicion);
		} catch (CursoServiceImpException e) {
			System.out.println(e.getMessage());
			curso = new Curso();
		}
		return curso;
	}

	private int buscarCurso(int codigo) throws CursoServiceImpException {
		int posicion = -1;
		int i = 0;
		boolean encontrado = false;
		while (encontrado == false && i<cursos.size()) {
			Curso aux = cursos.get(i);
			if (aux.getCodigo() == codigo){
				encontrado = true;
				posicion = i;
			}
			i++;
		}
		if (posicion == -1) {
			throw new CursoServiceImpException(CursoServiceImpException.COD_CURSO_NO_ENCONTRADO,
					CursoServiceImpException.MSG_CURSO_NO_ENCONTRADO);
		}
		return posicion;
	}

	@Override
	public List<Curso> getAll() {
		return cursos;
	}

	@Override
	public Curso update(Curso curso) {
		int posicion = -1;
		try {
			posicion = buscarCurso(curso.getCodigo());
			cursos.set(posicion, curso);
		} catch (CursoServiceImpException e) {
			System.out.println(e.getMessage());
		}
		return curso;
	}

	@Override
	public void delete(int codigo) {
		int posicion  = -1;
		try {
			posicion = buscarCurso(codigo);
			cursos.remove(posicion);
		} catch (CursoServiceImpException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

}
