package com.ipartek.formacion.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ipartek.formacion.controller.listeners.SessionListener;
import com.ipartek.formacion.dbms.pojo.Persona;

/**
 * Servlet implementation class ListarActivosServlet
 */
public class ListarActivosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(ListarActivosServlet.class);
	private RequestDispatcher rd;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListarActivosServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sessionID = request.getParameter(Constantes.PAR_SESSION_ID);
		if (sessionID == null) {
			try {
				HttpSession session = request.getSession(false);
				ServletContext ctx = session.getServletContext();
				List<Persona> personas = null;
				personas = (List<Persona>) ctx.getAttribute(Constantes.CTX_LISTADO_USUARIOS);
				LOG.trace("Personas activas en la aplicacion " + personas.toString());
				request.setAttribute(Constantes.ATT_LISTADO_USUARIOS, personas);
				rd = request.getRequestDispatcher(Constantes.JSP_LISTADO_ACTIVOS);
			} catch (NullPointerException e) {
				LOG.error(e.getMessage());
				request.setAttribute(Constantes.ATT_MENSAJE, "No se pueden otener los datos");
				rd = request.getRequestDispatcher(Constantes.JSP_HOME);
			} 
		}else{
			try {
				HttpSession session = SessionListener.getHttpSession(sessionID);
				session.invalidate();
				rd = request.getRequestDispatcher(Constantes.JSP_HOME);
			} catch (NullPointerException e) {
				LOG.error(e.getMessage());
			}
		}
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
