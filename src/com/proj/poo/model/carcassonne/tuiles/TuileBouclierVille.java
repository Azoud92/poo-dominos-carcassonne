package com.proj.poo.model.carcassonne.tuiles;

import com.proj.poo.model.carcassonne.tuiles.zones.ZoneQuartierVille;

public class TuileBouclierVille extends TuileCarcassonne {

	public TuileBouclierVille() {
		super();
	}

	@Override
	public void remplirTuile() {
		// TODO Auto-generated method stub
		// Le haut de la tuile est constitué d'un quartier, d'un quartier, d'un quartier
		tuile[0][0] = new ZoneQuartierVille();
		tuile[0][1] = new ZoneQuartierVille();
		tuile[0][2] = new ZoneQuartierVille();

		// La droite contient un quartier, un quartier, un quartier
		tuile[1][0] = new ZoneQuartierVille();
		tuile[1][1] = new ZoneQuartierVille();
		tuile[1][2] = new ZoneQuartierVille();

		// Le bas contient un quartier, un quartier, un quartier
		tuile[2][0] = new ZoneQuartierVille();
		tuile[2][1] = new ZoneQuartierVille();
		tuile[2][2] = new ZoneQuartierVille();

		// La gauche contient un quartier, un quartier, un quartier
		tuile[3][0] = new ZoneQuartierVille();
		tuile[3][1] = new ZoneQuartierVille();
		tuile[3][2] = new ZoneQuartierVille();
	}

}
