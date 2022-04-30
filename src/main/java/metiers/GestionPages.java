package metiers;

import java.util.ArrayList;

import beans.Ville;

public class GestionPages {

	private int debut;
	private int fin;

	public int getDebut() {
		return debut;
	}

	public void setDebut(int debut) {
		this.debut = debut;
	}

	public int getFin() {
		return fin;
	}

	public void setFin(int fin) {
		this.fin = fin;
	}

	public GestionPages() {

	}

	public void pagePrecedente(int debut, int fin, ArrayList<Ville> listVilles) {
		if(fin==listVilles.size()-1) {
			System.out.println("calcul : " + (fin-debut));
			System.out.println("taille : " + listVilles.size());
			this.fin = (listVilles.size()-2)-(fin-debut);
		}
		else {
			this.fin = fin - 50;
		}
		this.debut = debut - 50;
	}
	
	public void premierePage(int debut, int fin) {
		this.fin = 49;
		this.debut = 0;
	}
	
	public void pageSuivante(int debut, int fin, ArrayList<Ville> listVilles) {
		this.debut = fin + 1;
		if((fin+50)>listVilles.size()) {
			this.fin = listVilles.size()-1;
		}
		else {
			this.fin = fin + 50;
		}

	}
}
