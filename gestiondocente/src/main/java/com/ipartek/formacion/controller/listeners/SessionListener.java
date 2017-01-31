package com.ipartek.formacion.controller.listeners;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

import com.ipartek.formacion.controller.Constantes;
import com.ipartek.formacion.dbms.pojo.Persona;

/**
 * Application Lifecycle Listener implementation class SessionListener
 *
 */
public class SessionListener implements HttpSessionListener, HttpSessionAttributeListener, HttpSessionActivationListener, HttpSessionBindingListener {

	private static final Logger LOG = Logger.getLogger(SessionListener.class);
	private static int totalActiveSessions = 0;
	public static Map<String, HttpSession> map = new HashMap<String, HttpSession>();
	
    /**
     * Default constructor. 
     */
    public SessionListener() {
    	LOG.trace("Constrol de session");
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent se)  { 
    	String id = se.getSession().getId();
    	LOG.trace("Sesion de ID " + id + " Creada ");
    	totalActiveSessions ++;
    	map.put(id, se.getSession());
    }

	/**
     * @see HttpSessionBindingListener#valueBound(HttpSessionBindingEvent)
     */
    public void valueBound(HttpSessionBindingEvent event)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent se)  { 
        HttpSession session = se.getSession();
        List<Persona> personas = null;
        ServletContext ctx = session.getServletContext();
    	if (null != session.getAttribute(Constantes.SESSION_PERSONA)){
    		Persona persona = (Persona) session.getAttribute(Constantes.SESSION_PERSONA);
    		LOG.trace(persona.getNombre() + " se ha deslogueado");
    		personas = (List<Persona>) ctx.getAttribute(Constantes.CTX_LISTADO_USUARIOS);
    		personas.remove(persona);
    		ctx.setAttribute(Constantes.CTX_LISTADO_USUARIOS, personas);
    	}
    	totalActiveSessions--;
    	String sessionID = se.getSession().getId();
    	map.remove(sessionID);
    	LOG.trace(sessionID + " session eliminada");
    }

	/**
     * @see HttpSessionActivationListener#sessionDidActivate(HttpSessionEvent)
     */
    public void sessionDidActivate(HttpSessionEvent se)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
     */
    public void attributeAdded(HttpSessionBindingEvent se)  { 
    	HttpSession session = se.getSession();
    	List<Persona> personas = null;
    	ServletContext ctx = session.getServletContext();
    	// Cargar la lista de personas del contexto de la aplicacion
    	personas = (List<Persona>) ctx.getAttribute(Constantes.CTX_LISTADO_USUARIOS);
    	if (personas == null){
    		personas = new ArrayList<Persona>();
    	}
    	if(session.getAttribute(Constantes.SESSION_PERSONA) != null
    			&& se.getName().equals(Constantes.SESSION_PERSONA)){    		
    		Persona p = (Persona) session.getAttribute(Constantes.SESSION_PERSONA);
    		LOG.trace("Usuario "+ p.getNombre() +" registrado");
    		personas.add(p);
    		ctx.setAttribute(Constantes.CTX_LISTADO_USUARIOS, personas);
    	}
    }

	/**
     * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
     */
    public void attributeRemoved(HttpSessionBindingEvent se)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
     */
    public void attributeReplaced(HttpSessionBindingEvent se)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionActivationListener#sessionWillPassivate(HttpSessionEvent)
     */
    public void sessionWillPassivate(HttpSessionEvent se)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionBindingListener#valueUnbound(HttpSessionBindingEvent)
     */
    public void valueUnbound(HttpSessionBindingEvent event)  { 
         // TODO Auto-generated method stub
    }

    /**
     * 
     * @return the totalActiveSessions
     */
	public static int getTotalActiveSessions() {
		return totalActiveSessions;
	}

	public static HttpSession getHttpSession(String sessionID) {
		return map.get(sessionID);
	}
	
}
