package metier;

public class Cdifestivalband {
	
	private int 	band_id;
	private String 	band_name;
	private String 	band_biography;
	private String 	band_website;

	// Constructeur par défaut
	public Cdifestivalband(int £band_id,String £band_name,String £band_biography,String £band_website) {
		super();
		this.band_id=£band_id;
		this.band_name=£band_name;
		this.band_biography=£band_biography;
		this.band_website=£band_website;
	}

	public int getBand_id() {
		return band_id;
	}

	public void setBand_id(int £band_id) {
		this.band_id = £band_id;
	}

	public String getBand_name() {
		return band_name;
	}

	public void setBand_name(String £band_name) {
		this.band_name = £band_name;
	}

	public String getBand_biography() {
		return band_biography;
	}

	public void setBand_biography(String £band_biography) {
		this.band_biography = £band_biography;
	}

	public String getBand_website() {
		return band_website;
	}

	public void setBand_website(String £band_website) {
		this.band_website = £band_website;
	}

	@Override
	public String toString() {
		return "Cdifestival [band_id=" + band_id + ", band_name=" + band_name + ", band_biography=" + band_biography
				+ ", band_website=" + band_website + "]";
	}



}
