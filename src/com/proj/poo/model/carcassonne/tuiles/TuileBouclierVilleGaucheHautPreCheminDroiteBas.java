package com.proj.poo.model.carcassonne.tuiles;

import com.proj.poo.model.Tuile;
import com.proj.poo.model.carcassonne.tuiles.zones.ZoneChemin;
import com.proj.poo.model.carcassonne.tuiles.zones.ZoneLimiteVille;
import com.proj.poo.model.carcassonne.tuiles.zones.ZonePre;
import com.proj.poo.model.carcassonne.tuiles.zones.ZoneQuartierVille;

public class TuileBouclierVilleGaucheHautPreCheminDroiteBas extends Tuile {

	public TuileBouclierVilleGaucheHautPreCheminDroiteBas() {
		super(3);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void remplirTuile() {
		// TODO Auto-generated method stub
		// Le haut de la tuile est constitué d'un quartier, d'un quartier, et d'une limite de ville
		tuile[0][0] = new ZoneQuartierVille();
		tuile[0][1] = new ZoneQuartierVille();
		tuile[0][2] = new ZoneLimiteVille();

		// La droite contient un pré, un chemin, et un pré
		tuile[1][0] = new ZonePre();
		tuile[1][1] = new ZoneChemin();
		tuile[1][2] = new ZonePre();

		// Le bas contient un pré, un chemin, et un pré
		tuile[2][0] = new ZonePre();
		tuile[2][1] = new ZoneChemin();
		tuile[2][2] = new ZonePre();

		// La gauche contient seulement un quartier, un quartier, et une limite de ville
		tuile[3][0] = new ZoneQuartierVille();
		tuile[3][1] = new ZoneQuartierVille();
		tuile[3][2] = new ZoneLimiteVille();
	}

}
