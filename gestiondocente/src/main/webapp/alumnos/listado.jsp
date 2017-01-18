<%@page import="com.ipartek.formacion.dbms.pojo.Alumno"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Pagina de listado de alumnos</title>
</head>
<body>
	<header> <h1>Página listado de alumnos</h1></header>
	<main>
	<%
	/* Esto es un scriptlet */
	// Recogemos el atributo de la request
	List<Alumno> alumnos = (List<Alumno>) request.getAttribute("listado-alumnos");
	for (Alumno alumno: alumnos){
		out.println(alumno.toString());
	}
	%>	
	</main>
</body>
</html>