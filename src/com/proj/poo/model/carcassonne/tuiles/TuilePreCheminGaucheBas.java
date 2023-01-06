package com.proj.poo.model.carcassonne.tuiles;

import com.proj.poo.model.carcassonne.tuiles.cotes.CotePre;
import com.proj.poo.model.carcassonne.tuiles.cotes.CotePreCheminPre;

public class TuilePreCheminGaucheBas extends TuileCarcassonne {

	public TuilePreCheminGaucheBas() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void remplirTuile() {
		// TODO Auto-generated method stub

		// Le haut de la tuile est constitué de pré
		tuile[0] = new CotePre().getCote();

		// La droite également
		tuile[1] = new CotePre().getCote();

		// Le bas contient un pré, un chemin, et un pré
		tuile[2] = new CotePreCheminPre().getCote();

		// La gauche également
		tuile[3] = new CotePreCheminPre().getCote();
	}

}
