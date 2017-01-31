<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : 'es_ES'}" scope="page" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.ipartek.formacion.controller.i18nmessages" /> 
<!DOCTYPE html>
<html lang="${languaje}">
<head>
<meta charset="UTF-8">
<title>Página Inicial</title>
<link href="<c:url value="/css/styles.css" />" type="text/css" rel="stylesheet"/>
<script src="<c:url value="/js/codigo.js" />"> </script>
</head>
<body>
<header>
	<h1>Gestion Docente</h1>
	<nav>
		<ul>
			<li><a href=" <%= Constantes.SERVLET_ALUMNO  %>?<%=Constantes.PAR_OPERACION %>=<%=Constantes.OP_READ %>">G. Alumnos</a></li>
			<li><a href=" <%= Constantes.SERVLET_PROFESOR  %>?<%=Constantes.PAR_OPERACION %>=<%=Constantes.OP_READ %>">G. Profesores</a></li>
			<li><a href="<%= Constantes.SERVLET_CURSO  %>?<%=Constantes.PAR_OPERACION %>=<%=Constantes.OP_READ %>">G. Cursos</a></li>
		</ul>
		<%-- <a href="<%= Constantes.SERVLET_IDIOMA %>?<%= Constantes.PAR_IDIOMA %>=<%=Constantes.IDIOMA_CASTELLANO%>">Castellano </a>
		<a href="<%= Constantes.SERVLET_IDIOMA %>?<%= Constantes.PAR_IDIOMA %>=<%=Constantes.IDIOMA_EUSKERA%>">Euskera </a>
		<a href="<%= Constantes.SERVLET_IDIOMA %>?<%= Constantes.PAR_IDIOMA %>=<%=Constantes.IDIOMA_INGLES%>">Inglés </a> --%>
		<%
		if( session!=null && !session.isNew() && session.getAttribute(Constantes.SESSION_IDIOMA)!=null){
		%>
<!-- 		Sin parametros ya que va al get donde esta el metodo para cerrar sesion -->
		<p><a href="<%=Constantes.SERVLET_LOGIN%>">Cerrar Sesion</a></p>
		<p><a href="<%=Constantes.SERVLET_ACTIVOS%>">Listar usuarios activos</a></p>
		<%
			}
		%>
	</nav>
	<jsp:include page="mensajes.jsp"></jsp:include>
</header>