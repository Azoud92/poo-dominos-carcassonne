package com.proj.poo.model.carcassonne.tuiles;

import com.proj.poo.model.carcassonne.tuiles.cotes.CotePreCheminPre;
import com.proj.poo.model.carcassonne.tuiles.cotes.CoteQuartierQuartierLimite;

public class TuileVilleGaucheHautPreCheminDroiteBas extends TuileCarcassonne {

	public TuileVilleGaucheHautPreCheminDroiteBas() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void remplirTuile() {
		// TODO Auto-generated method stub
		// Le haut de la tuile est constitué d'un quartier, d'un quartier, et d'une limite de ville
		tuile[0] = new CoteQuartierQuartierLimite().getCote();

		// La droite contient un pré, un chemin, et un pré
		tuile[1] = new CotePreCheminPre().getCote();

		// Le bas contient un pré, un chemin, et un pré
		tuile[2] = new CotePreCheminPre().getCote();

		// La gauche contient seulement un quartier, un quartier, et une limite de ville
		tuile[3] = new CoteQuartierQuartierLimite().getCote();
	}

}
