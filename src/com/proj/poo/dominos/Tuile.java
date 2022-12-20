package com.proj.poo.dominos;

import java.util.Random;

import com.proj.poo.runner.Auxiliaire;

public class Tuile {

	private int[][] tuile; // contient les 3 chiffres contenus pour les 4 côtés
	
	// on remplit chaque tuile avec 3 chiffres de chaque côté
	public Tuile() {
		tuile = new int[4][3]; // 4 côtés, et 3 chiffres pour chacun d'entre eux
		
		Random rd = new Random();
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++) {
				tuile[i][j] = rd.nextInt(10); // 10 est exclu : on va de 0 à 9
			}
		}
	}
	
	// Rotation vers la droite
	public void rotation() {
		int[][] tuileRotated = { tuile[3], tuile[0], tuile[1], tuile[2] };
		tuile = tuileRotated;
	}
	
	// Les getters
	public int[] getHaut() { return tuile[0]; }	
	public int[] getDroite() { return tuile[1]; }	
	public int[] getBas() {	return tuile[2]; }	
	public int[] getGauche() { return tuile[3]; }
			
	public String toStringHaut() {
		return (" " + tuile[0][0] + " " + tuile[0][1] + " " + tuile[0][2] + "  ");
	}
	
	public String toStringMilieuBas() {
		return (tuile[3][0] + Auxiliaire.space(5) + tuile[1][0] + " \n" +
				tuile[3][1] + Auxiliaire.space(5) + tuile[1][1] + " \n" +
				tuile[3][2] + Auxiliaire.space(5) + tuile[1][2]) + " \n" +
				(" " + tuile[2][0] + " " + tuile[2][1] + " " + tuile[2][2]) + "  ";
	}
	
	@Override
	public String toString() {
		String t = "\n";
		t += " " + tuile[0][0] + " " + tuile[0][1] + " " + tuile[0][2] + "\n" + // chiffres du haut
				tuile[3][0] + Auxiliaire.space(5) + tuile[1][0] + "\n" + // premier chiffre à gauche et premier chiffre à droite
				tuile[3][1] + Auxiliaire.space(5) + tuile[1][1] + "\n" + // deuxième chiffre à gauche et deuxième chiffre à droite
				tuile[3][2] + Auxiliaire.space(5) + tuile[1][2] + "\n" + // troisième chiffre à gauche et troisième chiffre à droite
				" " + tuile[2][0] + " " + tuile[2][1] + " " + tuile[2][2] + "\n"; // chiffres du bas
		return t;
	}
	
	
	
}
