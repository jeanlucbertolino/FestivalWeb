<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
Page accueil <a href="http://localhost:8085/FestivalWebV2/WEB-INF/Scene.jsp">Vers la gestion des Scènes</a>
<% if (request.getAttribute("msgErreur") != null) { %>
<%=request.getAttribute("msgErreur") %>
<% } %>



</body>
</html>