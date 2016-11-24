<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ page import="java.util.*,java.io.*" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="http://localhost:8085/FestivalWeb/style/SceneCreat.css" />
<link rel="stylesheet" media="screen, print, handheld" type="text/css" href="http://http://localhost:8085/FestivalWeb/js/calendrier.js" />

<script type="text/javascript" src="http://localhost:8085/FestivalWeb/js/calendrier.js"></script>
<title>Creation Scene</title>
</head>
<body>
		<form id="form1" class="form" method="post" action="<%= request.getContextPath() %>/Festival/planification">

		<header>
			<p>
			<h1 id="titre1" style="font-style: bold;">PROGRAMMATION D'UNE SCENE</h1>
			</p>
		</header>
		<div class="div">
		<%
		ArrayList<String[]> liste = new ArrayList<String[]>();
		int id=0;
		String[] ligne = new String[5];
		String maj = (String) request.getAttribute("maj");
		
		if (request.getAttribute("maj") != null) {
			
			liste = (ArrayList<String[]>) request.getAttribute("liste");
			ligne = liste.get(0);
			id = (int) request.getAttribute("id");
						
			%>
			<fieldset>
			<input id="maj" type="hidden" name="maj" value="<%= maj%>"/>
			<input id="id" type="hidden" name="id" value="<%= id%>"/>
			<legend>
				<h2>MODIFICATION DE LA SCENE N° </h2>
			</legend>
			<p><label class="infos_id">
			<h2>
			<!-- Id    : -->
			<!--  <input type="text" name="id" id="numlect" maxlength="5" 
			size="5" readonly="readonly" -->
			<% 
				if (request.getAttribute("maj") != null)
				{ %><%=id %><% } 
			%>
			</h2>
			</label>
			</p>
			</br> 
			</br>
			</fieldset> 
			<%
						
			}
			%>
			<%
			if (request.getAttribute("maj") == null) {%>
			<fieldset>
			<legend>
				<h2>CREATION D'UNE SCENE</h2>
			</legend>
			<%} %>
		<fieldset class="field1" id="selgroupe">
			<legend>
				<h3>Choisissez un Groupe SVP</h3>
			</legend>
			<p><label class="groupe">Groupe .....: </label>
			<SELECT name="choixgroupe" id="selectgroupe" >
						<!-- Récupération de la liste des groupes autorisées -->	
			<%
			ArrayList<String[]> listeGroupe = (ArrayList<String[]>) request.getAttribute("listeGroupe");
			if (listeGroupe.size()!= 0) {
			for (int i=0; i<listeGroupe.size();i++)
			{
			String[] ligroupe = (String[]) listeGroupe.get(i);
			%>
				<option><%=ligroupe[1] %></option>
			<%
    		}
			}
			%>
			</SELECT>
			</fieldset>
			<br> 
			<br>
		
		<fieldset class="field2" id="seldate">
			<legend>
				<h3>Choisissez une Date SVP</h3>
			</legend>
			   <script>DateInput('orderdate', true, 'DD-MON-YYYY')</script>
    		   <input type="button" onClick="alert(this.form.orderdate.value)" value="sélectionner une date SVP">
    		   <!--  input type="button" onClick="datesel()" value="sélectionner une date SVP">  -->
    		   <!--  <p>Date : <input type="text" value="" name="choixdate" id="champ_date" size="12" maxlength="10"></p>  -->
			</fieldset>
			<br> 
			<br>

			<fieldset class="field3" id="selheure">
			<legend>
				<h3>Choisissez une Heure SVP</h3>
			</legend>
			<p><label class="choixheure">Heure .....: </label>
			<SELECT name="selheure" id="selectheure">
						<!-- Récupération de la liste des Heures autorisées -->	
			<%
			ArrayList<String[]> listeHeure = (ArrayList<String[]>) request.getAttribute("listeHeure");
			if (listeHeure.size()!= 0) {
			for (int i=0; i<listeHeure.size();i++)
			{
			String[] ligheure = (String[]) listeHeure.get(i);
			if (ligheure[1] !=" ") {
			%>
				<option><%=ligheure[1]%> <%=ligheure[2]%></option>
			<%
			}
			else {
				%>
				<option> </option>
			<%
				}
    		}
			}
			%>
				
			</SELECT>
			</fieldset>
			<br> 
			<br>
				
		
			<fieldset class="field4" id="selduree">
			<legend>
				<h3>Choisissez une Durée SVP</h3>
			</legend>
			<p><label class="choixduree">Durée .....: </label>
			<SELECT name="selduree" id="selectduree">
			<!-- Récupération de la liste des Durées autorisées -->	
			<%
			ArrayList<String[]> listeDuree = (ArrayList<String[]>) request.getAttribute("listeDuree");
			if (listeDuree.size()!= 0) {
			for (int i=0; i<listeDuree.size();i++)
			{
			String[] ligduree = (String[]) listeDuree.get(i);
			%>
				<option><%=ligduree[1] %></option>
			<%
    		}
			}
			%>			
			</SELECT>
			</fieldset>
			<br> 
			<br>							

		</DIV>
		<DIV class="div">	
			<fieldset class="field1">
			<legend>
				<h3>programmation groupe</h3>
			</legend>
  			<p id="titregroupe"><label for="groupesel">Groupe sélectionné : </label>
  			</p><input id="groupesel" type="text" name="groupesel" size="30" maxlength="10" readonly="readonly"
			<% 
				if (request.getAttribute("maj") != null) 
				{ %>value="<%=ligne[0] %>"<% } 
			%>
			/>
  			</fieldset>

			<br>
			<fieldset class="field2">
			<legend>
				<h3>programmation Date</h3>
			</legend>			
  			<p id="titredate"><label for="datesel">Date sélectionnée  : </label></p>
  			<input id="datesel" type="text" name="datesel" size="20" maxlength="10" 
  			<% 
				if (request.getAttribute("maj") != null) 
				{ %>value="<%=ligne[1] %>"<% } 
			%>
			/>
  			</fieldset>

			<br>
			<fieldset class="field3">
			<legend>
				<h3>programmation Heure</h3>
			</legend>
  			<p id="titreheure"><label for="heuresel">Heure sélectionnée : </label></p>
  			<input id="heuresel" type="text" name="heuresel" size="10" maxlength="5" readonly="readonly"
			<% 
				if (request.getAttribute("maj") != null) 
				{ %>value="<%=ligne[2] %>"<% } 
			%>
			/>
  			</fieldset>

			<br>
			<fieldset class="field4">
			<legend>
				<h3>programmation Durée</h3>
			</legend>
  			<p id="titreduree"><label for="dureesel">Durée sélectionnée  : </label></p>
  			<input id="dureesel" type="text" name="dureesel" size="5" maxlength="5" readonly="readonly"
  			<% 
				if (request.getAttribute("maj") != null) 
				{ %>value="<%=ligne[3] %>"<% } 
			%>
			/>
  			</fieldset>

			<br>
			
  		</DIV>
  		<DIV class="div">
  		  	<% 
			if (request.getAttribute("maj") == null)
				{ %>
  		  	<span><input type="submit" onclick="return valider()" value="create schedule"></span>
            <%} 
			else {%>
            <span><input type="submit" onclick="return modifier()" value="modify schedule"></span>
            <% } %>
			<span><input type="button" onclick="location.href='<%= request.getContextPath() %>/Festival/aff';" value="Planification"></span>
			<span><input type="button" onclick="location.href='<%= request.getContextPath() %>/Festival/consult';" value="Consultation"></span>
			
  		  	<span><input type="button" onclick="this.form.reset()" value="Clear"></span>
  		</DIV>

	</form>			

 <script type="text/javascript" src="http://localhost:8085/FestivalWeb/js/creatScene.js"></script>			

</body>
</html>