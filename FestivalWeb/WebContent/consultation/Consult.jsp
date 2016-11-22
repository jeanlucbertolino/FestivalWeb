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
<body><form action="<%= request.getContextPath() %>/Festival/planification/recherche" method="get">
		<header>
			<p>
			<h1 id="titre1" style="font-style: bold;">CONSULTATION D'UNE SCENE</h1>
			</p>
		</header>
<div class="centrage">

</br>
<%

ArrayList<String[]> liste =  (ArrayList<String[]>) request.getAttribute("listScene"); 

%>


</br>
<%-- Affichage des champs de Recherche --%>
<div id="recherche" class="centrage">
			<fieldset>
			<legend>
				<h2>Veuillez saisir vos critères de recherche SVP</h2>
			</legend>
			<div class="menu_horizontal">
			<span>
			<table border='0' align="center">
			<tr><td><h4>Groupe</h4></td><td><h4>Date</h4></td><td><h4>Heure</h4></td><td><h4>Durée</td></tr>
			<tr><td><input id="groupe" type="text" name="groupe" value=""/></td><td><input id="datec" type="text" name="datec" value=""/>
			</td><td><input id="heure" type="text" name="heure" value=""/></td><td><input id="duree" type="text" name="duree" value=""/>
			</td>
			<%-- Bouton Rechercher --%>
			<td>
			<span><input type="submit" value="Recherche" ></input></span>
			</td>
			</tr>
			</table>
			
			</span>
			</div>
			</fieldset>
</div>
<div class="centrage">

<h3><p>RUNING ORDER</p></h3></br>
			
<%-- Affichage des Scènes existantes --%>
<table border='1' align="center"  width="600">
<tr><td>Groupe</td><td>Date</td><td>Heure</td><td>Duree</td></tr>
<%

System.out.println("ListeScene ...:"+liste);
if (liste.size()!= 0) {
for (int i=0; i<liste.size();i++)
{
	String[] ligne = (String[]) liste.get(i);

%>
<tr>
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
<span><input type="button" onclick="location.href='<%= request.getContextPath() %>/Festival/';" value="Programmation" ></input></span>
</table>
</div>

</form>
</body>
</html>