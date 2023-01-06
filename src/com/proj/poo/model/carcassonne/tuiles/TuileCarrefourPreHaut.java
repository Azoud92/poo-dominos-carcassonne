package com.proj.poo.model.carcassonne.tuiles;

import com.proj.poo.model.carcassonne.tuiles.cotes.CotePreCheminPre;
import com.proj.poo.model.carcassonne.tuiles.zones.ZoneCarrefour;
import com.proj.poo.model.carcassonne.tuiles.zones.ZonePre;

public class TuileCarrefourPreHaut extends TuileCarcassonne {

	public TuileCarrefourPreHaut() {
		super();
		setCentre(new Centre(new ZoneCarrefour()));
		// TODO Auto-generated constructor stub
	}

	@Override
	public void remplirTuile() {
		// TODO Auto-generated method stub
		// Le haut de la tuile est constitué d'un pré, d'un pré, d'un pré
		tuile[0][0] = new ZonePre();
		tuile[0][1] = new ZonePre();
		tuile[0][2] = new ZonePre();

		// La droite contient un pré, un chemin, un pré
		tuile[1] = new CotePreCheminPre().getCote();

		// Le bas contient un pré, un chemin, un pré
		tuile[2] = new CotePreCheminPre().getCote();

		// La gauche contient un pré, un chemin, un pré
		tuile[3] = new CotePreCheminPre().getCote();
	}

}
