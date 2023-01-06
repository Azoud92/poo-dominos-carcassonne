package com.proj.poo.model.carcassonne.tuiles;

import com.proj.poo.model.carcassonne.tuiles.cotes.CotePreCheminPre;
import com.proj.poo.model.carcassonne.tuiles.cotes.CoteQuartier;
import com.proj.poo.model.carcassonne.tuiles.cotes.CoteQuartierQuartierLimite;

public class TuileVilleCheminBas extends TuileCarcassonne {

	public TuileVilleCheminBas() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void remplirTuile() {
		// TODO Auto-generated method stub
		// Le haut de la tuile est constitué d'un quartier, d'un quartier, d'un quartier
		tuile[0] = new CoteQuartier().getCote();

		// La droite contient un quartier, un quartier, une limite
		tuile[1] = new CoteQuartierQuartierLimite().getCote();

		// Le bas contient un pré, un chemin, un pré
		tuile[2] = new CotePreCheminPre().getCote();

		// La gauche contient un quartier, un quartier, une limite
		tuile[3] = new CoteQuartierQuartierLimite().getCote();
	}

}
