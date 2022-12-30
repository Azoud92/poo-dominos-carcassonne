package com.proj.poo.model.carcassonne.tuiles;

import com.proj.poo.model.Tuile;
import com.proj.poo.model.carcassonne.tuiles.zones.ZoneChemin;
import com.proj.poo.model.carcassonne.tuiles.zones.ZonePre;

public class TuilePreCheminGaucheBas extends Tuile {

	public TuilePreCheminGaucheBas() {
		super(3);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void remplirTuile() {
		// TODO Auto-generated method stub

		// Le haut de la tuile est constitué de pré
		tuile[0][0] = new ZonePre();
		tuile[0][1] = new ZonePre();
		tuile[0][2] = new ZonePre();

		// La droite également
		tuile[1][0] = new ZonePre();
		tuile[1][1] = new ZonePre();
		tuile[1][2] = new ZonePre();

		// Le bas contient un pré, un chemin, et un pré
		tuile[2][0] = new ZonePre();
		tuile[2][1] = new ZoneChemin();
		tuile[2][2] = new ZonePre();

		// La gauche également
		tuile[3][0] = new ZonePre();
		tuile[3][1] = new ZoneChemin();
		tuile[3][2] = new ZonePre();
	}

}
