package com.ipartek.formacion.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.dbms.pojo.Profesor;
import com.ipartek.formacion.service.ProfesorService;
import com.ipartek.formacion.service.ProfesorServiceImp;

/**
 * Servlet implementation class ProfesorServlet
 */
public class ProfesorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ProfesorService pS;
    private RequestDispatcher rd;
	
    /**
     * @see HttpServlet#HttpServlet()
     */

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init() throws ServletException {
		pS = new ProfesorServiceImp();
		super.init();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operacion = request.getParameter(Constantes.PAR_OPERACION);
		int op =-1;
		try {
			op = Integer.parseInt(operacion);
			switch (op) {
				case Constantes.OP_CREATE:
					rd = request.getRequestDispatcher(Constantes.JSP_FORMULARIO_PROFESOR);
					break;
				case Constantes.OP_READ:
					cargarListadoProfesores(request);
					break;
				case Constantes.OP_UPDATE:
					rd = request.getRequestDispatcher(Constantes.JSP_FORMULARIO_PROFESOR);
					break;
			default:
				cargarListadoProfesores(request);
				break;
			}
		} catch (Exception e) {
			response.sendRedirect(Constantes.JSP_HOME);
			return;
		}
		// Hace la redireccion
		rd.forward(request, response);
	}

	private void cargarListadoProfesores(HttpServletRequest request) {
		// Creamos un mapa y o corgamos con los valores de los profesores de ProfesorserviceImp
		Map<Integer, Profesor> profesores = pS.getAll();
		rd = request.getRequestDispatcher(Constantes.JSP_LISTADO_PROFESORES);
		// Añadimos el atributo a la request
		request.setAttribute(Constantes.ATT_LISTADO_PROFESORES, profesores);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Profesor profesor = null;
		String mensaje ="";
		try {
			profesor = recogerParametros(request);
			if (profesor.getCodigo()>Profesor.CODIGO_NULO) {
				pS.update(profesor);
				mensaje = "Profesor actualizado OK ";
				}
			else {
				pS.create(profesor);
				mensaje = "Profesor creado OK ";
				}
			
		} catch (Exception e) {
			// Redireccion al formulario del profesor
			rd = request.getRequestDispatcher(Constantes.JSP_FORMULARIO_PROFESOR);
			mensaje = e.getMessage();
		}
		request.setAttribute(Constantes.ATT_MENSAJE, mensaje);
		rd.forward(request, response);
	}

	private Profesor recogerParametros(HttpServletRequest request) throws Exception{
		Profesor profesor = new Profesor();
		try {
			profesor.setCodigo(Integer.parseInt(request.getParameter(Constantes.PAR_CODIGO)));
			profesor.setNombre(request.getParameter(Constantes.PAR_NOMBRE));
			profesor.setApellidos(request.getParameter(Constantes.PAR_APELLIDOS));
			profesor.setDni(request.getParameter(Constantes.PAR_DNI));
			profesor.setEmail(request.getParameter(Constantes.PAR_EMAIL));
			String fNacimiento = request.getParameter(Constantes.PAR_FNACIMIENTO);
			String pattern = "dd/MM/yy";
			SimpleDateFormat format = new SimpleDateFormat(pattern);
			profesor.setfNacimiento(format.parse(fNacimiento));
			
		} catch (Exception e) {
			throw new Exception( "Los datos no son correctos " + e.getMessage());
		}
		return profesor;
	}
	
	@Override
	public void destroy() {
		pS = null;
		super.destroy();
	}

	
}
