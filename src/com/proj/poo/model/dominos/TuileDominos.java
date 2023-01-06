package com.proj.poo.model.dominos;

import java.util.Random;

import com.proj.poo.model.Tuile;
import com.proj.poo.runner.Auxiliaire;

public class TuileDominos extends Tuile {
		
	public TuileDominos() {
		super(3);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void remplirTuile() {
		
		Random rd = new Random();
		// TODO Auto-generated method stub
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++) {
				tuile[i][j] = new IntegerZoneTuile(rd.nextInt(0, 10));
			}
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
