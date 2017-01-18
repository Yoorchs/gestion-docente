<%@page import="java.util.Map"%>
<%@page import="com.ipartek.formacion.dbms.pojo.Profesor"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Pagina de listado de alumnos</title>
</head>
<body>
	<header> <h1>Página listado de profesores</h1></header>
	<main>
	<%
	/* Esto es un scriptlet */
	// Recogemos el atributo de la request
	Map<Integer, Profesor> profesores = (Map<Integer, Profesor>) request.getAttribute("listado-profesores");
	
	for (Map.Entry<Integer, Profesor> entry : profesores.entrySet()) {
	    out.println("Clave del profesor =" + entry.getKey() + ", Email =" + entry.getValue().getEmail());
	}
	%>	
	</main>
</body>
</html>