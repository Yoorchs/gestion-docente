package com.ipartek.formacion.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.dbms.pojo.Alumno;
import com.ipartek.formacion.service.AlumnoService;
import com.ipartek.formacion.service.AlumnoServiceImp;

/**
 * Servlet implementation class AlumnoServlet
 */
public class AlumnoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AlumnoService aS;
	private RequestDispatcher rd;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	
	@Override
	public void init() throws ServletException {
		aS = new AlumnoServiceImp();
		super.init(); // Toda logica a implementar debe ir antes de la llamada a este metodo
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String operacion = request.getParameter(Constantes.PAR_OPERACION);
		int op = -1;
		
		try {
			op = Integer.parseInt(operacion);
			switch (op) {
			case Constantes.OP_CREATE:
				//Se va a redirigir a la pagina alumnos/alumno.jsp
				rd = request.getRequestDispatcher(Constantes.JSP_CREAR_ALUMNO);
				break;
			case Constantes.OP_READ:
				cargarListaAlumnos(request);
				break;
			case Constantes.OP_UPDATE:
				//aS = getById(codigo);
				//Se va a redirigir a la pagina alumnos/alumno.jsp
				rd = request.getRequestDispatcher(Constantes.JSP_CREAR_ALUMNO);
				break;
			default:
				cargarListaAlumnos(request);
				break;
			}
		} catch (Exception e) {
			// cargarListaAlumnos(request);	
			// Se redirige a la pagina principal en caso de que haya un error en el parametro de la request
			response.sendRedirect(Constantes.JSP_HOME); 
		}
		// Hace la redireccion
		rd.forward(request, response);

	}

	private void cargarListaAlumnos(HttpServletRequest request) {
		// Tambien se puede redirigir con la siguiente sentencia
		// response.sendRedirect("alumnos/listado.jsp"); --> Hace una redireccion limpia
		
		// Obtenemos el listado de alumos 
		List<Alumno> alumnos = aS.getAll();
		// fijamos la pagina dedestino
		rd = request.getRequestDispatcher(Constantes.JSP_LISTADO_ALUMNOS);
		// Añadimos el atributo a la request
		request.setAttribute(Constantes.ATT_LISTADO_ALUMNOS, alumnos);
	}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	}
	
	@Override
	public void destroy() {
		aS = null;
		super.destroy();
	}
}
