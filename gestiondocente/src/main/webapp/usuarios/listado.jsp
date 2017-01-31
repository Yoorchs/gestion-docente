<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ page import="com.ipartek.formacion.controller.Constantes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="../includes/header.jsp"></jsp:include>
<main>
	<header><h2>Listado de Usuarios</h2></header>
	<c:forEach var="persona" items="${listadoUsuarios}">
		<div>
			${persona.nombre} ${persona.apellidos} <a href="<%=Constantes.SERVLET_ACTIVOS%>?<%=Constantes.PAR_SESSION_ID %>=${persona.sessionID}"> Expulsar Usuario</a>
		</div>
	</c:forEach>
</main>
</body>
</html>