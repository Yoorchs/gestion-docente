package com.ipartek.formacion.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ipartek.formacion.dbms.pojo.Curso;
import com.ipartek.formacion.service.CursoserviceImp;

public class CursoServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CursoserviceImp cS;
	private RequestDispatcher rd;
	
	@Override
	public void init() throws ServletException {
		cS = new CursoserviceImp();
		super.init();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String operacion = req.getParameter(Constantes.PAR_OPERACION);
		int op = -1;
		try {
			op = Integer.parseInt(operacion);
			switch (op) {
			case Constantes.OP_CREATE:
				rd = req.getRequestDispatcher(Constantes.JSP_FORMULARIO_CURSO);
				break;
			case Constantes.OP_READ:
				cargarListaCursos(req);
				break;
			case Constantes.OP_DELETE:
				{
				int codigo = Integer.parseInt(req.getParameter(Constantes.PAR_CODIGO));
				cS.delete(codigo);
				req.setAttribute(Constantes.ATT_MENSAJE, "Curso eliminado");
				cargarListaCursos(req);
				break;
				}
			case Constantes.OP_UPDATE:
				{
				int codigo = Integer.parseInt(req.getParameter(Constantes.PAR_CODIGO));
				Curso curso = cS.getById(codigo);
				System.out.println(curso.toString());
				req.setAttribute(Constantes.ATT_CURSO, curso);
				rd = req.getRequestDispatcher(Constantes.JSP_FORMULARIO_CURSO);
				break;	
			}
			default:
				cargarListaCursos(req);
				break;
			}
		} catch (NumberFormatException e) {
			resp.sendRedirect(Constantes.JSP_HOME);
			return;
		}
		rd.forward(req, resp);
	}

	private void cargarListaCursos(HttpServletRequest req) {
		List<Curso> cursos = cS.getAll();
		rd = req.getRequestDispatcher(Constantes.JSP_LISTADO_CURSOS);
		req.setAttribute(Constantes.ATT_LISTADO_CURSOS, cursos);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, NumberFormatException {
		Curso curso = null;
		int codigo = - 1;
		String mensaje = "";
		try {
			codigo = Integer.parseInt(req.getParameter(Constantes.PAR_CODIGO));
			curso = recogerParametros(req);	
			curso.setCodigo(codigo);
			if (curso.getCodigo()>Curso.CODIGO_NULO){
				cS.update(curso);
				mensaje = "Curso actualizado";
			}else{
				cS.create(curso);
				mensaje = "Se ha dado de alta el curso";
				}
			cargarListaCursos(req);
		} catch (Exception e) {
			rd = req.getRequestDispatcher(Constantes.JSP_FORMULARIO_CURSO);
			mensaje = "Operacion ilegal";
			if (codigo == -1){
				rd = req.getRequestDispatcher(Constantes.JSP_LISTADO_CURSOS);
			}else{
				curso = cS.getById(codigo);
				rd = req.getRequestDispatcher(Constantes.JSP_FORMULARIO_CURSO);
				req.setAttribute(Constantes.ATT_CURSO, curso);
				mensaje = e.getMessage();
			}
			System.out.println("Error " + mensaje);
		}
		req.setAttribute(Constantes.ATT_MENSAJE, mensaje);
		rd.forward(req, resp);
	}

	private Curso recogerParametros(HttpServletRequest req) throws Exception {
		Curso curso = new Curso();
		try {
			curso.setCodigo(Integer.parseInt(req.getParameter(Constantes.PAR_CODIGO)));
			curso.setNombre(req.getParameter(Constantes.PAR_NOMBRE));
			curso.setDuracion(Integer.parseInt(req.getParameter(Constantes.PAR_DURACION)));
			// Fechas con sus formatos
			String pattern = "dd/MM/yyyy";
			SimpleDateFormat dateformat = new SimpleDateFormat(pattern);
			String finicio = req.getParameter(Constantes.PAR_FINICIO);
			String ffin = req.getParameter(Constantes.PAR_FFIN);
			if (finicio != null && ! "".equalsIgnoreCase(finicio)){
				curso.setfInicio(dateformat.parse(finicio));	
			}
			if (ffin!= null && ! "".equalsIgnoreCase(ffin)){
				curso.setfFinalizacion(dateformat.parse(ffin));
			}
			// curso.setAlumnos( req.getParameter(Constantes.PAR_LISTADO_ALUMNOS));
			// curso.setProfesor(req.getParameter(Constantes.PAR_PROFESOR));
		
		} catch (Exception e) {
			System.out.println("Error recogiendo parametros " + e.getMessage());
			throw new Exception( e.getMessage());
		}
		return curso;
	}


	@Override
	public void destroy() {
		this.cS = null;
		super.destroy();
	}
}
