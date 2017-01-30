package com.ipartek.formacion.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ipartek.formacion.dbms.pojo.Alumno;
import com.ipartek.formacion.service.AlumnoService;
import com.ipartek.formacion.service.AlumnoServiceImp;

/**
 * Servlet implementation class AlumnoServlet
 */
public class AlumnoServlet extends HttpServlet {
	private static final Logger LOG = Logger.getLogger(AlumnoServlet.class);
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
				rd = request.getRequestDispatcher(Constantes.JSP_FORMULARIO_ALUMNO);
				break;
			case Constantes.OP_READ:
				cargarListaAlumnos(request);
				break;
			case Constantes.OP_UPDATE:
				{	
				int codigo = -1;
				codigo = Integer.parseInt(request.getParameter(Constantes.PAR_CODIGO));
				Alumno alumno = aS.getById(codigo);
				request.setAttribute(Constantes.ATT_ALUMNO, alumno);
				//Se va a redirigir a la pagina alumnos/alumno.jsp
				rd = request.getRequestDispatcher(Constantes.JSP_FORMULARIO_ALUMNO);
				break;
				}
			case Constantes.OP_DELETE:
				{	
				int codigo = -1;
				codigo = Integer.parseInt(request.getParameter(Constantes.PAR_CODIGO));
				aS.delete(codigo);
				request.setAttribute(Constantes.ATT_MENSAJE, "El alumno se ha dado de baja");
				cargarListaAlumnos(request);
				break;
				}
			default:
				cargarListaAlumnos(request);
				break;
			}
		} catch (Exception e) {
			// cargarListaAlumnos(request);	
			// Se redirige a la pagina principal en caso de que haya un error en el parametro de la request
			LOG.error(e.getMessage() + "Valor del codigo de alumno: " + request.getParameter(Constantes.PAR_CODIGO));
			response.sendRedirect(Constantes.JSP_HOME); 
			return;
		}
		// Hace la redireccion
		rd.forward(request, response);

	}

	private void cargarListaAlumnos(HttpServletRequest request) {
		LOG.trace(" ");
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Alumno alumno = null;
		int codigo = -1;
		String mensaje ="";
		try {
			codigo = Integer.parseInt(request.getParameter(Constantes.PAR_CODIGO));
			alumno = recogerParametros(request);
			//Procesaremos UPDATE o INSERT
			if (alumno.getCodigo()>Alumno.CODIGO_NULO){ // Update
				aS.update(alumno);
				mensaje = " El alumno se ha actualizado OK";
			}else { // Create
				aS.create(alumno);
				mensaje = " El alumno se ha creado OK";
			}
			cargarListaAlumnos(request);
		} catch (Exception e) {
			//Redirigimos al formulario con el dispatcher
			rd = request.getRequestDispatcher(Constantes.JSP_FORMULARIO_ALUMNO);
			mensaje = "Operacion inesperada";
			if (codigo == -1) {
				rd = request.getRequestDispatcher(Constantes.JSP_LISTADO_ALUMNOS);
			} else {
				alumno = aS.getById(codigo);
				rd = request.getRequestDispatcher(Constantes.JSP_FORMULARIO_ALUMNO);
				request.setAttribute(Constantes.ATT_ALUMNO, alumno);
				mensaje = e.getMessage();
			}
			System.out.println(mensaje);
			LOG.error(e.getMessage());
		}
		request.setAttribute(Constantes.ATT_MENSAJE, mensaje);
		rd.forward(request, response);
	}
	
	private Alumno recogerParametros(HttpServletRequest request) throws Exception {
		Alumno alumno = new Alumno();
		try {
			alumno.setCodigo(Integer.parseInt(request.getParameter(Constantes.PAR_CODIGO)));
			alumno.setNombre(request.getParameter(Constantes.PAR_NOMBRE));
			alumno.setApellidos(request.getParameter(Constantes.PAR_APELLIDOS));
			alumno.setDireccion(request.getParameter(Constantes.PAR_DIRECCION));
			alumno.setDni(request.getParameter(Constantes.PAR_DNI));
			alumno.setEmail(request.getParameter(Constantes.PAR_EMAIL));
			
			String hermanos  = request.getParameter(Constantes.PAR_NHERMANOS);
			if ("".equalsIgnoreCase(hermanos)) {
				hermanos = "0";
			}
			alumno.setnHermanos(Integer.parseInt(hermanos));
			alumno.setActivo(Boolean.parseBoolean(Constantes.PAR_ACTIVO));
			String fechaNacimiento = request.getParameter(Constantes.PAR_FNACIMIENTO);
			String pattern = "dd/MM/yyyy";
			SimpleDateFormat dateformat = new SimpleDateFormat(pattern);
			alumno.setfNacimiento(dateformat.parse(fechaNacimiento));
			
		}catch (Exception e) {
			LOG.error(e.getMessage());
			throw new Exception(" Los datos no son validos " + e.getMessage());
		}
		return alumno;
	}

	@Override
	public void destroy() {
		aS = null;
		super.destroy();
	}
}
