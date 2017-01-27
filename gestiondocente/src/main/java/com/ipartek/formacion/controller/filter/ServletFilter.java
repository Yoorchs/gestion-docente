package com.ipartek.formacion.controller.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.ipartek.formacion.controller.Constantes;

/**
 * Listener que filtra las peticiones hechas a los servlets
 */


/**
 * Servlet Filter implementation class ServletFilter
 */
public class ServletFilter implements Filter {

    /**
     * Default constructor. 
     */
    public ServletFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// Obtenemos la sesion 
		// No se puede obtener la sesion con request.getSession ya que request no es un objeto de tipo HttpServletRequest
		// Para ello se hace un casting implicito
		if (request instanceof HttpServletRequest){
			HttpSession session = ((HttpServletRequest) request).getSession(false);
			
			String url = ((HttpServletRequest) request).getServletPath();
			
			// Caso a --> Acceso permitido : Cuando existe la sesion y que existe el parametro idioma o cuando vas a login
			// Caso b --> Acceso restringido
			
			// Si no existe la sesion vamos a comprobar si el usuario quiere ir a login.do
			System.out.println(session.getAttribute(Constantes.SESSION_IDIOMA));
			if (session != null && session.getAttribute(Constantes.SESSION_IDIOMA) != null || checkWebPage(url)){
					// Se deja pasar a login acceder con la siguiente sentencia
				System.out.println(session.getAttribute(Constantes.SESSION_IDIOMA));
				chain.doFilter(request, response);
				} else {
					// No se permite el acceso y se redirige a home asi, se añade el return porque se puede quedar bloqueado
					((HttpServletResponse) response).sendRedirect(Constantes.JSP_HOME);
					return;
				}
		} else {
			chain.doFilter(request, response);
		}
	}

	private boolean checkWebPage(final String path) {
		boolean exito = false;
		System.out.println(path + " " + "/" + Constantes.SERVLET_LOGIN);
		if (path.equalsIgnoreCase("/" + Constantes.SERVLET_LOGIN)){
			exito = true;
		}
		return exito;
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
