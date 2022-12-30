package com.proj.poo.model.carcassonne.tuiles;

import com.proj.poo.model.Tuile;
import com.proj.poo.model.carcassonne.tuiles.zones.ZoneLimiteVille;
import com.proj.poo.model.carcassonne.tuiles.zones.ZonePre;
import com.proj.poo.model.carcassonne.tuiles.zones.ZoneQuartierVille;

public class TuileVilleGaucheDroitePre extends Tuile {

	public TuileVilleGaucheDroitePre(int nbZones) {
		super(nbZones);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void remplirTuile() {
		// TODO Auto-generated method stub
		// Le haut de la tuile est constitué d'un pré, d'un pré, d'un pré
		tuile[0][0] = new ZonePre();
		tuile[0][1] = new ZonePre();
		tuile[0][2] = new ZonePre();

		// La droite contient une limite, un quartier, une limite
		tuile[1][0] = new ZoneLimiteVille();
		tuile[1][1] = new ZoneQuartierVille();
		tuile[1][2] = new ZoneLimiteVille();

		// Le bas contient un pré, un pré, et un pré
		tuile[2][0] = new ZonePre();
		tuile[2][1] = new ZonePre();
		tuile[2][2] = new ZonePre();

		// La gauche contient une limite, un quartier, une limite
		tuile[3][0] = new ZoneLimiteVille();
		tuile[3][1] = new ZoneQuartierVille();
		tuile[3][2] = new ZoneLimiteVille();
	}

}
