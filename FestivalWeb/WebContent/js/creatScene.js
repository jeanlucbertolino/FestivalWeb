  // <!--
function controler(){
	
	console.log("contrôles saisie en cours ...");

	// <!-- Contrôle des infos saisies
	var rq,rq1,rq2,rq3,rq4;
	rq1=("Veuillez renseigner un groupe SVP !!!");	
	rq2=("Veuillez renseigner une date SVP !!!");
	rq3=("Veuillez renseigner une heure SVP !!!");
	rq4=("Veuillez renseigner une duree SVP !!!");

	// <!-- Informations Groupe
	var groupeselvalue=document.getElementById("groupesel").value;
	var groupesel=document.getElementById("titregroupe");
	var groupefield=document.getElementById("selgroupe");
	// <!-- Informations Date
	var dateselvalue=document.getElementById("datesel").value;
	var datesel=document.getElementById("titredate");
	var datefield=document.getElementById("seldate");

	// <!-- Informations Heure
	var heureselvalue=document.getElementById("heuresel").value;
	var heuresel=document.getElementById("titreheure");
	var heurefield=document.getElementById("selheure");
	
	// <!-- Informations Duree
	var dureeselvalue=document.getElementById("dureesel").value;
	var dureesel=document.getElementById("titreduree");
	var dureefield=document.getElementById("selduree");
	
	// <!-- contrôle saisie Groupe	
	  	
		if (groupeselvalue=="") {
	  		alert(rq1);
	  		groupesel.style.backgroundColor="red";
	 		groupefield.className="erreur";
	 		return false;
	  		}
	  	else {
	  		// <!-- contrôle saisie Date	
	  		if (dateselvalue=="") {
	  			alert(rq2);
	  			datesel.style.backgroundColor="red";
	  			datefield.className="erreur";
	  			return false;
	  		}
		  	else {
		  		// <!-- contrôle saisie Date	
		  		if (heureselvalue=="") {
		  			alert(rq3);
		  			heuresel.style.backgroundColor="red";
		  			heurefield.className="erreur";
		  			return false;
		  		}
		  		else {
		  			if (dureeselvalue=="") {
			  			alert(rq4);
			  			dureesel.style.backgroundColor="red";
			  			dureefield.className="erreur";
			  			return false;
			  		}
		  			else {
		  				return true;
/*		  			// <!-- contrôles validés - Affichage des infos saisies
		  			// <!-- Groupe
		  			var groupe = document.getElementById("selectgroupe").value;
		  			document.getElementById('groupesel').value = groupe;
		  			
		  			// <!-- Date
		  		// <!-- var date = document.getElementById("selectdate").value;
		  		// <!-- document.getElementById('datesel').value = date;
		  			// <!-- Heure
		  			var heure = document.getElementById("selectheure").value;
		  			document.getElementById('heuresel').value = heure;
		  			
		  			// <!-- Durée
		  			var duree = document.getElementById("selectduree").value;
		  			document.getElementById('dureesel').value = duree;		  			
		  			return true; */
		  			}    
		  			}
		  		}
	  		}
	  	}

	 // <!--
function change(select) {
	var choice = select.selectedIndex;
	var texte = select.options[choice].text;
	alert(texte);
	// <!--document.getElementById('groupesel').value = texte;
	return true;
}	  	
	  	
function change_groupe() {
	
	var select = document.getElementById("selectgroupe");
	var texte = select.options[selectedIndex].text;
	alert(texte);
	// <!--document.getElementById('groupesel').value = texte;
	  return false;
	  	}
// <!--
function valider(){
	// <!-- Affichage des infos saisies
	// <!-- Groupe
	var groupe = document.getElementById("selectgroupe").value;
	document.getElementById('groupesel').value = groupe;
	
	// <!-- Date
// <!-- var date = document.getElementById("selectdate").value;
// <!-- document.getElementById('datesel').value = date;
	// <!-- Heure
	var heure = document.getElementById("selectheure").value;
	document.getElementById('heuresel').value = heure;
	
	// <!-- Durée
	var duree = document.getElementById("selectduree").value;
	document.getElementById('dureesel').value = duree;		  			
	var xboolean=controler();
	return xboolean;
	
	}	
function modifier(){
	// <!-- Affichage des infos saisies
	// <!-- Groupe
	var groupe = document.getElementById("selectgroupe").value;
	if (groupe!="") {
	document.getElementById('groupesel').value = groupe;
	}
	// <!-- Date
// <!-- var date = document.getElementById("selectdate").value;
// <!-- document.getElementById('datesel').value = date;
	// <!-- Heure
	var heure = document.getElementById("selectheure").value;
	if (heure!="") {
	document.getElementById('heuresel').value = heure;
	}
	// <!-- Durée
	var duree = document.getElementById("selectduree").value;
	if (duree!="") {
	document.getElementById('dureesel').value = duree;
	}
	var xboolean=controler();
	return xboolean;
	
	}
 
