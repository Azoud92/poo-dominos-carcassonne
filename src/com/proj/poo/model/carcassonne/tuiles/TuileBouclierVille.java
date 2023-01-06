package com.proj.poo.model.carcassonne.tuiles;

import com.proj.poo.model.carcassonne.tuiles.cotes.CoteQuartier;

public class TuileBouclierVille extends TuileCarcassonne {

	public TuileBouclierVille() {
		super();
	}

	@Override
	public void remplirTuile() {
		// TODO Auto-generated method stub
		// Le haut de la tuile est constitué d'un quartier, d'un quartier, d'un quartier
		tuile[0] = new CoteQuartier().getCote();

		// La droite contient un quartier, un quartier, un quartier
		tuile[1] = new CoteQuartier().getCote();

		// Le bas contient un quartier, un quartier, un quartier
		tuile[2] = new CoteQuartier().getCote();

		// La gauche contient un quartier, un quartier, un quartier
		tuile[3] = new CoteQuartier().getCote();
	}

}
