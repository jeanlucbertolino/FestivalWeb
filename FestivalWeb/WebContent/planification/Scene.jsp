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
<%
String sup = (String) request.getAttribute("sup");

if (request.getAttribute("sup") == null) {
	if (request.getAttribute("maj") == null) {
%>
<h2><p>Dernier enregistrement créé</p></h2>
<%
	} else {
		%>
		<h2><p>Dernier enregistrement modifié</p></h2>
		<%
	}
}
else {
%>
<div class="centrage">
<h2><p>Dernier enregistrement supprimé</p></h2>
<% 	
}
%>
</br>
<table border="1" align="center"  width="600">
<%

int id = (int) request.getAttribute("id");
String groupe = (String) request.getAttribute("groupe");
String datec = (String) request.getAttribute("datec");
String heure = (String) request.getAttribute("heure");
String duree = (String) request.getAttribute("duree");

ArrayList<String[]> liste =  (ArrayList<String[]>) request.getAttribute("listScene"); 

%>

<tr>
<td><h4><p>Id</p></h4></td><td><h4><p>Groupe</h4></td><td><h4><p>Date</h4></td><td><h4><p>Heure</h4></td><td><h4><p>Duree</h4></td></TR>
<tr><td><%=id %></td><td><%=groupe %></td><td><%=datec %></td><td><%=heure %></td><td><%=duree %></td></TR>
</table></br>
</div>
</br>
<div class="centrage">

<h3><p>LISTE DES GROUPES</p></h3></br>
			
<%-- Affichage des Scènes existantes --%>
<table border='1' align="center"  width="800">
<tr><td>Suppression</td><td>Modification</td><td>Groupe</td><td>Date</td><td>Heure</td><td>Duree</td></tr>
<%
System.out.println("sup ...:"+sup);
System.out.println("id ...:"+id);
System.out.println("groupe ...:"+groupe);
System.out.println("datec ...:"+datec);
System.out.println("heure ...:"+heure);
System.out.println("duree ...:"+duree);
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
<span><input type="submit" name="program" value="Programmation" ></input></span>
</table>
</div>

</form>
</body>
</html>