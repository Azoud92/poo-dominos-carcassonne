package com.proj.poo.model.carcassonne.tuiles;

import com.proj.poo.model.Tuile;
import com.proj.poo.model.ZoneTuile;
import com.proj.poo.runner.Auxiliaire;

public abstract class TuileCarcassonne extends Tuile {

	private Centre centre;
	
	public TuileCarcassonne() {
		super(3);
	}
	
	public Centre getCentre() {
		return centre;
	}
	
	public void setCentre(Centre centre) { this.centre = centre; }

	public class Centre {
		private ZoneTuile zt_centre;
		
		public Centre(ZoneTuile zt_centre) {
			this.zt_centre = zt_centre;
		}

		public ZoneTuile getZt_centre() {
			return zt_centre;
		}
	}
	
	public String toStringHaut() {
		return (" " + tuile[0][0].getValue() + " " + tuile[0][1].getValue() + " " + tuile[0][2].getValue() + "  ");
	}
	
	public String toStringMilieuBas() {
		return (tuile[3][0].getValue() + Auxiliaire.space(5) + tuile[1][0].getValue() + " \n" +
				tuile[3][1].getValue() + Auxiliaire.space(5) + tuile[1][1].getValue() + " \n" +
				tuile[3][2].getValue() + Auxiliaire.space(5) + tuile[1][2].getValue()) + " \n" +
				(" " + tuile[2][0].getValue() + " " + tuile[2][1].getValue() + " " + tuile[2][2].getValue()) + "  ";
	}
	
	@Override
	public String toString() {
		String t = "\n";
		t += " " + tuile[0][0].getValue() + " " + tuile[0][1].getValue() + " " + tuile[0][2].getValue() + "\n" + // chiffres du haut
				tuile[3][0].getValue() + Auxiliaire.space(5) + tuile[1][0].getValue() + "\n" + // premier chiffre à gauche et premier chiffre à droite
				tuile[3][1].getValue() + Auxiliaire.space(5) + tuile[1][1].getValue() + "\n" + // deuxième chiffre à gauche et deuxième chiffre à droite
				tuile[3][2].getValue() + Auxiliaire.space(5) + tuile[1][2].getValue() + "\n" + // troisième chiffre à gauche et troisième chiffre à droite
				" " + tuile[2][0].getValue() + " " + tuile[2][1].getValue() + " " + tuile[2][2].getValue() + "\n"; // chiffres du bas
		return t;
	}

	

}
