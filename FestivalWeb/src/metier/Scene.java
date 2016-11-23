package metier;

import java.time.LocalDate;
import java.util.Date;

import technique.DateException;


/**
 * Cette classe est définie dans le but de gérer la programmation d'une scène 
 * pour un groupe à une date précise pour une durée estimée.
 * {@docRoot} (chemin où se trouve la documentation depuis l'origine du projet (src)
 */
public class Scene{

	@Override
	public String toString() {
		return "Scene [groupe=" + groupe + ", date=" + datec + ", heure=" + heure + ", duree=" + duree + "]";
	}

	public	int			idscene;
	public String 		groupe;
	public String 		datec;
	public String 		heure;
	public String 		duree;

	// Constructeur par défaut
	public Scene(int £id,String £groupe,String £date,String £heure,String £duree)  throws  DateException {
		super();
		// Contrôle de saisie de la date
//		String dateRaw = affTxtDate(£date);
		
//		if(dateRaw.isEmpty()) {
		if (£date==null) {
			throw new DateException();}

			else {
				this.idscene=£id;
				this.groupe=£groupe;
				this.datec=£date;
				this.heure=£heure;
				this.duree=£duree;		
			}
		}
		public String getDatec() {
		return datec;
	}
	public void setDatec(String datec) {
		this.datec = datec;
	}
		public String getGroupe() {
			return groupe;
		}
		public String getDate() {
			return datec;
		}
		public void setGroupe(String groupe) {
			this.groupe = groupe;
		}
		public void setDate(String £date) {
			this.datec = £date;
		}
		public void setHeure(String £heure) {
			this.heure = £heure;
		}
		public void setDuree(String £duree) {
			this.duree = £duree;
		}
		public String getHeure() {
			return heure;
		}
		public String getDuree() {
			return duree;
		}
		public String affTxtDate(Date £date) {
			// Affichage de la date sélectionnée
			String dateRaw = £date.toString();
			return dateRaw;
		}
		public int getIdscene() {
			return idscene;
		}
		public void setIdscene(int idscene) {
			this.idscene = idscene;
		}
	}
