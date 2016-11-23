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
			
				<option>       </option>
				<option> AC/DC </option> 
				<option> IRON MAIDEN </option>
				<option> KISS </option>
				<option> AEROSMITH </option>
				<option> DEEP PURPLE </option>
				<option> DEPECHE MODE </option> 
				<option> THE CURE </option>
				<option> U2 </option>
				<option> TEARS FOR FEARS </option>
				<option> THE ROLLING STONES </option>
			
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
			
				<option>       </option>			
				<option> 13:00 </option> 
				<option> 13:30 </option>
				<option> 14:00 </option>
				<option> 14:30 </option>
				<option> 15:00 </option>
				<option> 15:30 </option> 
				<option> 16:00 </option>
				<option> 16:30 </option>
				<option> 17:00 </option>
				<option> 17:30 </option>
				<option> 18:00 </option> 
				<option> 18:30 </option>
				<option> 19:00 </option>
				<option> 19:30 </option>
				<option> 20:00 </option>
				<option> 20:30 </option> 
				<option> 21:00 </option>
				<option> 21:30 </option>
				<option> 22:00 </option>
				<option> 22:30 </option>
				<option> 23:00 </option>
				
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
			
				<option>       </option>			
				<option> 1:00 </option> 
				<option> 1:30 </option>
				<option> 2:00 </option>
			
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