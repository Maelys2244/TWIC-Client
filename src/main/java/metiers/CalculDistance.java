package metiers;

import java.util.ArrayList;

import beans.Ville;

public class CalculDistance {
	Ville ville1; 
	Ville ville2;
	double distance;
	double r;
	
	public Ville getVille1() {
		return ville1;
	}

	public void setVille1(Ville ville1) {
		this.ville1 = ville1;
	}

	public Ville getVille2() {
		return ville2;
	}

	public void setVille2(Ville ville2) {
		this.ville2 = ville2;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public CalculDistance() {
		ville1 = new Ville();
		ville2 = new Ville();
		distance = 0;
		r= 6372.795477598;
	}
	
	public double calculteurDistance(String nomVille1, String nomVille2,ArrayList<Ville> listVilles) {
		for(int i = 0 ; i<listVilles.size();i++) {
			if (listVilles.get(i).getNomCommune().equals(nomVille1)){
				ville1 = listVilles.get(i);
				break;
			}
		}
		for(int j = 0 ; j<listVilles.size();j++) {
			if (listVilles.get(j).getNomCommune().equals(nomVille2)){
				ville2 = listVilles.get(j);
				break;
			}
		}
		double lat1 = ville1.getLatitude();
		double long1 = ville1.getLongitude();
		double lat2 = ville2.getLatitude();
		double long2 = ville2.getLongitude();
		distance = r*Math.acos(Math.sin((lat1/180)*Math.PI)*Math.sin((lat2/180)*Math.PI)+Math.cos((lat1/180)*Math.PI)*Math.cos((lat2/180)*Math.PI)*Math.cos((long1/180)*Math.PI-(long2/180)*Math.PI));
		return distance;
	}

	
}
