package com.proj.poo.model.carcassonne.tuiles;

import com.proj.poo.model.carcassonne.tuiles.zones.ZoneChemin;
import com.proj.poo.model.carcassonne.tuiles.zones.ZoneLimiteVille;
import com.proj.poo.model.carcassonne.tuiles.zones.ZonePre;
import com.proj.poo.model.carcassonne.tuiles.zones.ZoneQuartierVille;

public class TuileBouclierVilleCheminBas extends TuileCarcassonne {

	public TuileBouclierVilleCheminBas() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void remplirTuile() {
		// TODO Auto-generated method stub
		// Le haut de la tuile est constitué d'un quartier, d'un quartier, d'un quartier
		tuile[0][0] = new ZoneQuartierVille();
		tuile[0][1] = new ZoneQuartierVille();
		tuile[0][2] = new ZoneQuartierVille();

		// La droite contient un quartier, un quartier, une limite
		tuile[1][0] = new ZoneQuartierVille();
		tuile[1][1] = new ZoneQuartierVille();
		tuile[1][2] = new ZoneLimiteVille();

		// Le bas contient un pré, un chemin, un pré
		tuile[2][0] = new ZonePre();
		tuile[2][1] = new ZoneChemin();
		tuile[2][2] = new ZonePre();

		// La gauche contient un quartier, un quartier, une limite
		tuile[3][0] = new ZoneQuartierVille();
		tuile[3][1] = new ZoneQuartierVille();
		tuile[3][2] = new ZoneLimiteVille();
	}
	
}
