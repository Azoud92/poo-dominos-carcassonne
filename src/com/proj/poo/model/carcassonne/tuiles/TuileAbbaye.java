package com.proj.poo.model.carcassonne.tuiles;

import com.proj.poo.model.carcassonne.tuiles.zones.ZoneAbbaye;
import com.proj.poo.model.carcassonne.tuiles.zones.ZonePre;

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
		tuile[0][0] = new ZonePre();
		tuile[0][1] = new ZonePre();
		tuile[0][2] = new ZonePre();

		// La droite contient un pré, un pré, un pré
		tuile[1][0] = new ZonePre();
		tuile[1][1] = new ZonePre();
		tuile[1][2] = new ZonePre();

		// Le bas contient un pré, un pré, un pré
		tuile[2][0] = new ZonePre();
		tuile[2][1] = new ZonePre();
		tuile[2][2] = new ZonePre();

		// La gauche contient un pré, un pré, un pré
		tuile[3][0] = new ZonePre();
		tuile[3][1] = new ZonePre();
		tuile[3][2] = new ZonePre();
	}

}