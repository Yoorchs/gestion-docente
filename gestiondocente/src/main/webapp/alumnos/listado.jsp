<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@page import="com.ipartek.formacion.dbms.pojo.Alumno"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"></jsp:include>
	<main>
	<%
	/* Esto es un scriptlet */
	// Recogemos el atributo de la request
	List<Alumno> alumnos = (List<Alumno>) request.getAttribute(Constantes.ATT_LISTADO_ALUMNOS);
	// CRUD
	%>
	<a href="<%= Constantes.SERVLET_ALUMNO %>?<%=Constantes.PAR_OPERACION %>=<%=Constantes.OP_CREATE %>">Crear Alumno</a>
	<%
	for (Alumno alumno: alumnos){
		String btnDelete = "<a href=" + Constantes.SERVLET_ALUMNO+"?"+ Constantes.PAR_OPERACION + "=" 
		+ Constantes.OP_DELETE + "&" + Constantes.PAR_CODIGO +"=" + alumno.getCodigo() + ">Borrar </a>";
		out.println("<p>" + alumno.toString() + "<a href=" + Constantes.SERVLET_ALUMNO + "?" + 
	Constantes.PAR_OPERACION  + "=" + Constantes.OP_UPDATE + "&" + Constantes.PAR_CODIGO + 
	"="  + alumno.getCodigo() + "> Editar </a>" + btnDelete + " </p>");
	}
	%>	
	</main>
<%@ include file="../includes/footer.html" %>
</body>
</html>