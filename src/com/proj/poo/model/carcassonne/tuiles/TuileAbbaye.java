package com.proj.poo.model.carcassonne.tuiles;

import com.proj.poo.model.carcassonne.tuiles.cotes.CotePre;
import com.proj.poo.model.carcassonne.tuiles.zones.ZoneAbbaye;

public class TuileAbbaye extends TuileCarcassonne {

	public TuileAbbaye() {
		super();
		setCentre(new Centre(new ZoneAbbaye()));
		// TODO Auto-generated constructor stub
	}

	@Override
	public void remplirTuile() {
		// TODO Auto-generated method stub
		// Le haut de la tuile est constitué d'un pré, d'un pré, d'un pré
		tuile[0] = new CotePre().getCote();

		// La droite contient un pré, un pré, un pré
		tuile[1] = new CotePre().getCote();

		// Le bas contient un pré, un pré, un pré
		tuile[2] = new CotePre().getCote();

		// La gauche contient un pré, un pré, un pré
		tuile[3] = new CotePre().getCote();
	}

}
