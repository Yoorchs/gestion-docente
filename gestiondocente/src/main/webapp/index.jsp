<%@page import="com.ipartek.formacion.controller.Constantes"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : 'es_ES'}" scope="page" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.ipartek.formacion.controller.i18nmessages" /> 
<jsp:include page="/includes/header.jsp"/>
<main>
<!-- 	El mensaje lo coje de las i18n de los valores de index.mensaje e index.nombreApp -->
	<fmt:message key="index.mensaje" />
	<fmt:message key="index.nombreApp" />
	<form action="<%=Constantes.SERVLET_LOGIN %>" method="post">
		<p><input type="text" placeholder="<fmt:message key="index.login.username"/>" name="<%=Constantes.PAR_USUARIO %>" id="<%=Constantes.PAR_USUARIO %>"></p>
		<p><input type="password" placeholder="<fmt:message key="index.login.password"/>" name="<%=Constantes.PAR_PASSWORD %>" id="<%=Constantes.PAR_PASSWORD %>"></p>
		<p><label for="<%=Constantes.PAR_IDIOMA%>"><fmt:message key="index.login.idioma"/></label></p>
		<p>
			<select name="<%=Constantes.PAR_IDIOMA%>" id="<%=Constantes.PAR_IDIOMA%>">
				<option value="<%=Constantes.IDIOMA_CASTELLANO%>"><fmt:message key="idioma.castellano"/></option>
				<option value="<%=Constantes.IDIOMA_EUSKERA%>"><fmt:message key="idioma.euskera"/></option>
				<option value="<%=Constantes.IDIOMA_INGLES%>"><fmt:message key="idioma.ingles"/></option>
			</select>
		</p>
		<button type="submit" value="<fmt:message key="index.login.enviar"/>"><fmt:message key="index.login.enviar"/></button>
	</form>
</main>
<%@ include file="includes/footer.html" %>
</body>
</html>