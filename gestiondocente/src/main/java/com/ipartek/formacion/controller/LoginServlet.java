package com.ipartek.formacion.controller;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RequestDispatcher rd;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		// TODO Auto-generated method stub
		Locale locale = new Locale("es_ES");
		//Obtiene el atributo lenguaje de la sesion
		//Si al recoger la sesion se le pasa true o nada, si no existe la sesion, se crea
		//Si es false, obtiene la ya existente y si no existe una, peta
		String language = (String)request.getSession(true).getAttribute("language");
		if (language != null){
			locale = new Locale(language);
		}
		ResourceBundle messages = null;
		try {
			messages = ResourceBundle.getBundle("com.ipartek.formacion.controller.i18nmessages", locale);
		} catch (Exception e) {
			System.out.println("Error :" + e.getMessage());
		}
		rd = request.getRequestDispatcher(Constantes.JSP_HOME);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String user = "admin";
		final String pass = "admin";
		String username = request.getParameter(Constantes.PAR_USUARIO);
		String password = request.getParameter(Constantes.PAR_PASSWORD);
		
		// Las condiciones van en orden, user y luego pass
		// Se compara primero el user con los datos introducidos ya que el valor de user nunca es nulo 
		if (user.equals(username) && pass.equals(password)) {
			// Crearemos la sesion, puede tener 3 valores, 
			// true fuerza una sesion nueva
			// false recoges una existente
			// sin parametros crea una nueva
			HttpSession session = request.getSession(true);
			// Fijamos la duracion de una sesion inactiva
			session.setMaxInactiveInterval(60*10);
			// Cargamos la variable idioma
			// La guardamos en una variable de sesion
			String lang = request.getParameter(Constantes.PAR_IDIOMA);
			int idioma = Integer.parseInt(lang);
			String locale ="";
			switch (idioma) {
			case Constantes.IDIOMA_CASTELLANO:
				locale ="es_ES";
				break;
			case Constantes.IDIOMA_EUSKERA:
				locale ="eu_ES";
				break;
			case Constantes.IDIOMA_INGLES:
				locale ="en_EN";
				break;
			default:
				locale ="es_ES";
				break;
			}
			session.setAttribute(Constantes.SESSION_IDIOMA, locale);
			// Redireccionamos a una pagina
			rd = request.getRequestDispatcher(Constantes.JSP_HOME);
			
			
		} else {
			// Mostramos un mensaje indicando que los datos introducidos no son correctos
			String mensajeError = "Usuario y/o contraseña incorrectos";
			// Redireccionamos a index.jsp
			request.setAttribute(Constantes.ATT_MENSAJE, mensajeError);
			rd = request.getRequestDispatcher(Constantes.JSP_HOME);
		}
		rd.forward(request, response);
	}

}
