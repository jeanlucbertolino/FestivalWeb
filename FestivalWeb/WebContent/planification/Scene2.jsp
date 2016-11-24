<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,java.io.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="http://localhost:8085/FestivalWeb/style/SceneCreat.css" />
<title>Gestion des Scenes</title>
</head>
<body><form action="<%= request.getContextPath() %>/Festival/" method="post">
		<header>
			<p>
			<h1 id="titre1" style="font-style: bold;">PLANIFICATION D'UNE SCENE</h1>
			</p>
		</header>
<div class="centrage">

</br>
<%

ArrayList<String[]> liste =  (ArrayList<String[]>) request.getAttribute("listScene"); 

%>


</br>
<div class="centrage">

<h3><p>LISTE DES GROUPES</p></h3></br>
<h4><p>Modification ou suppression d'une programmation</p></h4></br>
			
<%-- Affichage des Scènes existantes --%>
<table border='1' align="center"  width="800">
<tr><td>Suppression</td><td>Modification</td><td>Groupe</td><td>Date</td><td>Heure</td><td>Duree</td></tr>
<%

System.out.println("ListeScene ...:"+liste);
if (liste.size()!= 0) {
for (int i=0; i<liste.size();i++)
{
	String[] ligne = (String[]) liste.get(i);

%>
<tr><td><a href="<%= request.getContextPath() %>/Festival/supp?id=<%= ligne[0]%>">X</a></td><td><a href="<%= request.getContextPath() %>/Festival/modif?id=<%= ligne[0]%>"><%=ligne[0]%></a></td>
<td><%=ligne[1] %></td><td><%=ligne[2] %></td><td><%=ligne[3] %></td><td><%=ligne[4] %></td></tr>
<%
    }
}
%>
</table>
</div>
</br>
</br>
</br>

<div id="div_retour" class="centrage">
<table border="" align="center">
<span><input type="submit" value="Programmation" ></input></span><span><input type="button" onclick="location.href='<%= request.getContextPath() %>/Festival/consult';" value="Consultation"></span>
</table>
</div>

</form>
</body>
</html>