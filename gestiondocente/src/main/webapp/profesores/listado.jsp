<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@page import="java.util.Map"%>
<%@page import="com.ipartek.formacion.dbms.pojo.Profesor"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"></jsp:include>
	<main>
	<%
	/* Esto es un scriptlet */
	// Recogemos el atributo de la request
	Map<Integer, Profesor> profesores = (Map<Integer, Profesor>) request.getAttribute(Constantes.ATT_LISTADO_PROFESORES);
	%>
	<a href="<%= Constantes.SERVLET_PROFESOR %>?<%=Constantes.PAR_OPERACION %>=<%=Constantes.OP_CREATE %>">Introducir nuevo profesor</a>
	<%
	for (Map.Entry<Integer, Profesor> entry : profesores.entrySet()) {
	    out.println( "<p>" + entry.getValue().toString() + "</p>");
	}
	%>	
	</main>
<%@ include file="../includes/footer.html" %>
</body>
</html>