package metier;

public class Groupe {

	private String id;
	private String libelle;

	// Constructeur par défaut
	public Groupe(String £id,String £libelle) {
		super();
		this.id=£id;
		this.libelle=£libelle;

	}

	@Override
	public String toString() {
		return "Theme [id groupe =" + id + ", libelle groupe =" + libelle + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}



	//

}
