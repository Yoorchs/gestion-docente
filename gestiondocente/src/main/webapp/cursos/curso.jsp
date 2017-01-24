<%@page import="java.lang.reflect.Method"%>
<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@page import="com.ipartek.formacion.dbms.pojo.Curso"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"></jsp:include>
<%
	Curso curso = (Curso) request.getAttribute(Constantes.ATT_CURSO);
	String titulo ="";
	if (curso == null){
		titulo="Crear curso";
		curso = new Curso();
	}else{
		titulo="Actualizar curso";
	}
%>
<main>
	<header><h2><%=titulo %></h2></header>
	<form action="<%=Constantes.SERVLET_CURSO%>" method="post">
			<input type="hidden" name="<%=Constantes.PAR_CODIGO %>" id="<%=Constantes.PAR_CODIGO %>" value="<%=curso.getCodigo()%>">
		<div>
			<label for="<%=Constantes.PAR_NOMBRE%>">Nombre: </label>
			<input type="text" placeholder="Introduzca el nombre del curso..."  name="<%= Constantes.PAR_NOMBRE%>" id="<%= Constantes.PAR_NOMBRE%>" value="<%=curso.getNombre()%>">
		</div>
		<div>
			<label for="<%=Constantes.PAR_DURACION%>">Duracion: </label>
			<input type="text" placeholder="Introduzca la duracion del curso..."  name="<%= Constantes.PAR_DURACION%>" id="<%= Constantes.PAR_DURACION%>" value="<%=curso.getDuracion()%>">
		</div>
		<input type="submit" value="Enviar" />
	</form>
</main>
<%@ include file="../includes/footer.html" %>
</body>
</html>