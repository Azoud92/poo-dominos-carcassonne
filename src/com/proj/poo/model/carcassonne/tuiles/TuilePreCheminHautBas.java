package com.proj.poo.model.carcassonne.tuiles;

import com.proj.poo.model.carcassonne.tuiles.cotes.CotePre;
import com.proj.poo.model.carcassonne.tuiles.cotes.CotePreCheminPre;

public class TuilePreCheminHautBas extends TuileCarcassonne {

	public TuilePreCheminHautBas() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void remplirTuile() {
		// TODO Auto-generated method stub
		// Le haut de la tuile est constitué d'un pré, d'un chemin, d'un pré
		tuile[0] = new CotePreCheminPre().getCote();

		// La droite contient un pré, un pré, un pré
		tuile[1] = new CotePre().getCote();

		// Le bas contient un pré, un chemin, et un pré
		tuile[2] = new CotePreCheminPre().getCote();

		// La gauche contient un pré, un pré, un pré
		tuile[3] = new CotePre().getCote();
	}

}
