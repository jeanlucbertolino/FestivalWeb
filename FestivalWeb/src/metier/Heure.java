package metier;

public class Heure {

	@Override
	public String toString() {
		return "Heure [numHeure=" + numHeure + ", numMinute=" + numMinute + "]";
	}

	private int idHeure;
	private String numHeure;
	private String numMinute;

	// Constructeur par défaut
	public Heure(int £idHeure,String £NumHeure,String £NumMinute) {
		super();
		this.idHeure=£idHeure;
		this.numHeure=£NumHeure;
		this.numMinute=£NumMinute;

	}

	public int getIdHeure() {
		return idHeure;
	}

	public void setIdHeure(int £idHeure) {
		this.idHeure = £idHeure;
	}

	public String getNumHeure() {
		return numHeure;
	}

	public void setNumHeure(String £numHeure) {
		numHeure = £numHeure;
	}

	public String getNumMinute() {
		return numMinute;
	}

	public void setNumMinute(String £numMinute) {
		numMinute = £numMinute;
	}



}
