package beans;

public class Ville {
	String codePostal;
	String nomCommune;
	String libelleAcheminement;
	double latitude;
	double longitude;
	String codeCommuneINSEE;
	String ligne5;

	public String getLigne5() {
		return ligne5;
	}

	public void setLigne5(String ligne5) {
		this.ligne5 = ligne5;
	}

	public Ville() {

	}



	public Ville(String codePostal, String nomCommune, String libelleAcheminement, double latitude, double longitude,
			String codeCommuneINSEE, String ligne5) {
		super();
		this.codePostal = codePostal;
		this.nomCommune = nomCommune;
		this.libelleAcheminement = libelleAcheminement;
		this.latitude = latitude;
		this.longitude = longitude;
		this.codeCommuneINSEE = codeCommuneINSEE;
		this.ligne5 = ligne5;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getNomCommune() {
		return nomCommune;
	}

	public void setNomCommune(String nomCommune) {
		this.nomCommune = nomCommune;
	}

	public String getLibelleAcheminement() {
		return libelleAcheminement;
	}

	public void setLibelleAcheminement(String libelleAcheminement) {
		this.libelleAcheminement = libelleAcheminement;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getCodeCommuneINSEE() {
		return codeCommuneINSEE;
	}

	public void setCodeCommuneINSEE(String codeCommuneINSEE) {
		this.codeCommuneINSEE = codeCommuneINSEE;
	}

}
