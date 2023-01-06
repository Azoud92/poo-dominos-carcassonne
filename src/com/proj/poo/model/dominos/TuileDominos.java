package com.proj.poo.model.dominos;

import java.util.Random;

import com.proj.poo.model.Cote;
import com.proj.poo.model.Tuile;
import com.proj.poo.model.dominos.cotes.CoteCinqSeptTrois;
import com.proj.poo.model.dominos.cotes.CoteCinqZeroHuit;
import com.proj.poo.model.dominos.cotes.CoteDeuxZeroZero;
import com.proj.poo.model.dominos.cotes.CoteNeufUnUn;
import com.proj.poo.model.dominos.cotes.CoteQuatreHuitSept;
import com.proj.poo.model.dominos.cotes.CoteSixSixNeuf;
import com.proj.poo.model.dominos.cotes.CoteUnHuitQuatre;
import com.proj.poo.model.dominos.cotes.CoteUnNeufTrois;
import com.proj.poo.model.dominos.cotes.CoteUnSeptCinq;
import com.proj.poo.model.dominos.cotes.CoteUnSixZero;
import com.proj.poo.model.dominos.cotes.CoteUnUnUn;
import com.proj.poo.model.dominos.cotes.CoteZeroCinqZero;
import com.proj.poo.model.dominos.cotes.CoteZeroNeufDeux;
import com.proj.poo.runner.Auxiliaire;

public class TuileDominos extends Tuile {
		
	public TuileDominos() {
		super(3);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void remplirTuile() {
		
		Random rd = new Random();
		
		Cote[] c = new Cote[13];
		c[0] = new CoteCinqSeptTrois();
		c[1] = new CoteCinqZeroHuit();
		c[2] = new CoteDeuxZeroZero();
		c[3] = new CoteNeufUnUn();
		c[4] = new CoteQuatreHuitSept();
		c[5] = new CoteSixSixNeuf();
		c[6] = new CoteUnHuitQuatre();
		c[7] = new CoteUnNeufTrois();
		c[8] = new CoteUnSeptCinq();
		c[9] = new CoteUnSixZero();
		c[10] = new CoteUnUnUn();
		c[11] = new CoteZeroCinqZero();
		c[12] = new CoteZeroNeufDeux();
		
		// TODO Auto-generated method stub
		for (int i = 0; i < 4; i++) {
			tuile[i] = c[rd.nextInt(0, 13)].getCote();
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
