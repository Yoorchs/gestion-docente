<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@page import="com.ipartek.formacion.dbms.pojo.Curso" %>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"></jsp:include>

<main>
	<%
	List<Curso> cursos = (List<Curso>) request.getAttribute(Constantes.ATT_LISTADO_CURSOS);
	%>
	<a href="<%=Constantes.SERVLET_CURSO %>?<%=Constantes.PAR_OPERACION %>=<%=Constantes.OP_CREATE%>">Crear Nuevo Curso</a>
	<% for (Curso curso: cursos){
		String btnDelete = "<a href=" + Constantes.SERVLET_CURSO + "?" + Constantes.PAR_OPERACION + "=" 
				+ Constantes.OP_DELETE + "&" + Constantes.PAR_CODIGO + "=" + curso.getCodigo() + ">Borrar </a>";
		String btnEdit = "<a href=" + Constantes.SERVLET_CURSO + "?" + Constantes.PAR_OPERACION  + "=" 
				+ Constantes.OP_UPDATE + "&" + Constantes.PAR_CODIGO + "="  + curso.getCodigo() + "> Editar </a>";
		
		out.println("<p>" + curso.toString() + btnEdit + btnDelete + " </p>");
	} %>
</main>
<%@ include file="../includes/footer.html" %>
</body>
</html>