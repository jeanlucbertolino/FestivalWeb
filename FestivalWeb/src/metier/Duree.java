package metier;

public class Duree {

	private int idDuree;
	private String numDuree;

	// Constructeur par défaut
	public Duree(int £id,String £libelle) {
		super();
		this.idDuree=£id;
		this.numDuree=£libelle;
	}

	public String getNumDuree() {
		return numDuree;
	}

	public int getId() {
		return idDuree;
	}

	public void setNumDuree(String £numDuree) {
		this.numDuree = £numDuree;
	}

	public void setId(int £id) {
		this.idDuree = £id;
	}

	@Override
	public String toString() {
		return "Duree [id=" + idDuree + ", numDuree=" + numDuree + "]";
	}
}

