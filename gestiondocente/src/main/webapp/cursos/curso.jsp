<%@page import="java.lang.reflect.Method"%>
<%@page import="java.util.GregorianCalendar"%>
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
			<input type="number" placeholder="Introduzca la duracion del curso..."  name="<%= Constantes.PAR_DURACION%>" id="<%= Constantes.PAR_DURACION%>" value="<%=curso.getDuracion()%>">
		</div>
		<%
		String dateIn = "";
		String dateFin = "";
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(curso.getfInicio());
		dateIn = gc.get(GregorianCalendar.DAY_OF_MONTH) + "/" + gc.get(GregorianCalendar.MONTH) +1  + "/" + gc.get(GregorianCalendar.YEAR);
		gc.setTime(curso.getfFinalizacion());
		dateFin = gc.get(GregorianCalendar.DAY_OF_MONTH) + "/" + gc.get(GregorianCalendar.MONTH) +1  + "/" + gc.get(GregorianCalendar.YEAR);
		%>
		<div>
			<label for="<%=Constantes.PAR_FINICIO%>">Fecha de Inicio: </label>
			<input type="text" placeholder="Introduzca la fecha de inicio del curso..."  name="<%= Constantes.PAR_FINICIO%>" id="<%= Constantes.PAR_FINICIO%>" value="<%=dateIn%>">
		</div>
		<div>
			<label for="<%=Constantes.PAR_FFIN%>">Fecha de Finalizacion: </label>
			<input type="text" placeholder="Introduzca la fecha de finalizacion del curso..."  name="<%= Constantes.PAR_FFIN%>" id="<%= Constantes.PAR_FFIN%>" value="<%=dateFin%>">
		</div>
		<input type="submit" value="Enviar" />
	</form>
</main>
<%@ include file="../includes/footer.html" %>
</body>
</html>